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

@Api(name = "groupsubscribersendpoint", namespace = @ApiNamespace(ownerDomain = "shawnkrecker.com", ownerName = "shawnkrecker.com", packagePath = "eventation"))
public class GroupSubscribersEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listGroupSubscribers")
	public CollectionResponse<GroupSubscribers> listGroupSubscribers(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<GroupSubscribers> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from GroupSubscribers as GroupSubscribers");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<GroupSubscribers>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (GroupSubscribers obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<GroupSubscribers> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getGroupSubscribers")
	public GroupSubscribers getGroupSubscribers(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		GroupSubscribers groupsubscribers = null;
		try {
			groupsubscribers = mgr.find(GroupSubscribers.class, id);
		} finally {
			mgr.close();
		}
		return groupsubscribers;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param groupsubscribers the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertGroupSubscribers")
	public GroupSubscribers insertGroupSubscribers(
			GroupSubscribers groupsubscribers) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsGroupSubscribers(groupsubscribers)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(groupsubscribers);
		} finally {
			mgr.close();
		}
		return groupsubscribers;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param groupsubscribers the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateGroupSubscribers")
	public GroupSubscribers updateGroupSubscribers(
			GroupSubscribers groupsubscribers) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsGroupSubscribers(groupsubscribers)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(groupsubscribers);
		} finally {
			mgr.close();
		}
		return groupsubscribers;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeGroupSubscribers")
	public void removeGroupSubscribers(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			GroupSubscribers groupsubscribers = mgr.find(
					GroupSubscribers.class, id);
			mgr.remove(groupsubscribers);
		} finally {
			mgr.close();
		}
	}

	private boolean containsGroupSubscribers(GroupSubscribers groupsubscribers) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			GroupSubscribers item = mgr.find(GroupSubscribers.class,
					groupsubscribers.getId());
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
