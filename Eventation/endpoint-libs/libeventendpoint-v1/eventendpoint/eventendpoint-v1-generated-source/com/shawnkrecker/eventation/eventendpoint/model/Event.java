/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-11-22 19:59:01 UTC)
 * on 2013-12-04 at 23:36:24 UTC 
 * Modify at your own risk.
 */

package com.shawnkrecker.eventation.eventendpoint.model;

/**
 * Model definition for Event.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the eventendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Event extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer attendingCount;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime eventDate;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String eventDescription;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String eventName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String groupName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getAttendingCount() {
    return attendingCount;
  }

  /**
   * @param attendingCount attendingCount or {@code null} for none
   */
  public Event setAttendingCount(java.lang.Integer attendingCount) {
    this.attendingCount = attendingCount;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getEventDate() {
    return eventDate;
  }

  /**
   * @param eventDate eventDate or {@code null} for none
   */
  public Event setEventDate(com.google.api.client.util.DateTime eventDate) {
    this.eventDate = eventDate;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEventDescription() {
    return eventDescription;
  }

  /**
   * @param eventDescription eventDescription or {@code null} for none
   */
  public Event setEventDescription(java.lang.String eventDescription) {
    this.eventDescription = eventDescription;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEventName() {
    return eventName;
  }

  /**
   * @param eventName eventName or {@code null} for none
   */
  public Event setEventName(java.lang.String eventName) {
    this.eventName = eventName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getGroupName() {
    return groupName;
  }

  /**
   * @param groupName groupName or {@code null} for none
   */
  public Event setGroupName(java.lang.String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Event setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  @Override
  public Event set(String fieldName, Object value) {
    return (Event) super.set(fieldName, value);
  }

  @Override
  public Event clone() {
    return (Event) super.clone();
  }

}
