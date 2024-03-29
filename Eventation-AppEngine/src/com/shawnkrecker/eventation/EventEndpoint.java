package com.shawnkrecker.eventation;

import com.shawnkrecker.eventation.EMF;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "eventendpoint", namespace = @ApiNamespace(ownerDomain = "shawnkrecker.com", ownerName = "shawnkrecker.com", packagePath = "eventation"))
public class EventEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listEvent")
	public CollectionResponse<Event> listEvent(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Event> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Event as Event");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Event>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Event obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Event> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getEvent")
	public Event getEvent(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Event event = null;
		try {
			event = mgr.find(Event.class, id);
		} finally {
			mgr.close();
		}
		return event;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param event the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertEvent")
	public Event insertEvent(Event event) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsEvent(event)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(event);	
			
			
			//created event
			//sending out GCM messages
			
			String projectID = "1089823764628";
			String apiKey = "AIzaSyC40uPMW3XWHcA1c70cUh9RxfQp9oX6-pg";
			
			String requestHeader = "content-type: \"application/json\" Header : \"project_id\": " + projectID+
					"Header: \"Authorization\", \"key=" + apiKey + "\"";
			
			String groupName = event.getGroupName();
			
			GroupSubscribersEndpoint gSE = new GroupSubscribersEndpoint();
			
			CollectionResponse<GroupSubscribers> gS = gSE.listGroupSubscribers(null,100);
			
			Collection<GroupSubscribers> list = gS.getItems();
			
			String registeredIDs = "\"registration_ids\": [";
					
					for(GroupSubscribers g: list){
						if(g.getGroupID().equals(groupName)){
							registeredIDs += "\"" + g.getRegID() + "\", ";
						}
					}
					
//					registeredIDs = registeredIDs.substring(0, registeredIDs.length() - 4);
			
			registeredIDs +="]";
			
			String data = "{ \"data\": { \"eventname\": \"" + event.getEventName() + 
					"\", \"event_groupname\": \"" + groupName +
					"\", \"event_description\": \"" + event.getGroupName() +
					"\", \"event_date\": \"" + event.getEventDate().toString() +
					"\" }, }";	
			
			
			MessageEndpoint message = new MessageEndpoint();
			
			try{
				message.sendMessage(data);
			} catch( IOException ex){
				
			}
			
			
			
			
		} finally {
			mgr.close();
		}
		return event;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param event the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateEvent")
	public Event updateEvent(Event event) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsEvent(event)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(event);
		} finally {
			mgr.close();
		}
		return event;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeEvent")
	public void removeEvent(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Event event = mgr.find(Event.class, id);
			mgr.remove(event);
		} finally {
			mgr.close();
		}
	}

	private boolean containsEvent(Event event) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Event item = mgr.find(Event.class, event.getId());
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
