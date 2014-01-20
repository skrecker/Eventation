package com.shawnkrecker.eventation;

import com.shawnkrecker.eventation.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "groupendpoint", namespace = @ApiNamespace(ownerDomain = "shawnkrecker.com", ownerName = "shawnkrecker.com", packagePath = "eventation"))
public class GroupEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listGroup")
	public CollectionResponse<Group> listGroup(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Group> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Group as Group");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Group>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Group obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Group> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getGroup")
	public Group getGroup(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		Group group = null;
		try {
			group = mgr.find(Group.class, id);
		} finally {
			mgr.close();
		}
		return group;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param group the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertGroup")
	public Group insertGroup(Group group) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsGroup(group)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(group);
		} finally {
			mgr.close();
		}
		return group;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param group the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateGroup")
	public Group updateGroup(Group group) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsGroup(group)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(group);
		} finally {
			mgr.close();
		}
		return group;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeGroup")
	public void removeGroup(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		try {
			Group group = mgr.find(Group.class, id);
			mgr.remove(group);
		} finally {
			mgr.close();
		}
	}

	private boolean containsGroup(Group group) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Group item = mgr.find(Group.class, group.getGroupName());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
