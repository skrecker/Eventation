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

package com.shawnkrecker.eventation.eventendpoint;

/**
 * Service definition for Eventendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link EventendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Eventendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the eventendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://event-ation.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "eventendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Eventendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Eventendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getEvent".
   *
   * This request holds the parameters needed by the the eventendpoint server.  After setting any
   * optional parameters, call the {@link GetEvent#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetEvent getEvent(java.lang.Long id) throws java.io.IOException {
    GetEvent result = new GetEvent(id);
    initialize(result);
    return result;
  }

  public class GetEvent extends EventendpointRequest<com.shawnkrecker.eventation.eventendpoint.model.Event> {

    private static final String REST_PATH = "event/{id}";

    /**
     * Create a request for the method "getEvent".
     *
     * This request holds the parameters needed by the the eventendpoint server.  After setting any
     * optional parameters, call the {@link GetEvent#execute()} method to invoke the remote operation.
     * <p> {@link
     * GetEvent#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetEvent(java.lang.Long id) {
      super(Eventendpoint.this, "GET", REST_PATH, null, com.shawnkrecker.eventation.eventendpoint.model.Event.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetEvent setAlt(java.lang.String alt) {
      return (GetEvent) super.setAlt(alt);
    }

    @Override
    public GetEvent setFields(java.lang.String fields) {
      return (GetEvent) super.setFields(fields);
    }

    @Override
    public GetEvent setKey(java.lang.String key) {
      return (GetEvent) super.setKey(key);
    }

    @Override
    public GetEvent setOauthToken(java.lang.String oauthToken) {
      return (GetEvent) super.setOauthToken(oauthToken);
    }

    @Override
    public GetEvent setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetEvent) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetEvent setQuotaUser(java.lang.String quotaUser) {
      return (GetEvent) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetEvent setUserIp(java.lang.String userIp) {
      return (GetEvent) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetEvent setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetEvent set(String parameterName, Object value) {
      return (GetEvent) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertEvent".
   *
   * This request holds the parameters needed by the the eventendpoint server.  After setting any
   * optional parameters, call the {@link InsertEvent#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.shawnkrecker.eventation.eventendpoint.model.Event}
   * @return the request
   */
  public InsertEvent insertEvent(com.shawnkrecker.eventation.eventendpoint.model.Event content) throws java.io.IOException {
    InsertEvent result = new InsertEvent(content);
    initialize(result);
    return result;
  }

  public class InsertEvent extends EventendpointRequest<com.shawnkrecker.eventation.eventendpoint.model.Event> {

    private static final String REST_PATH = "event";

    /**
     * Create a request for the method "insertEvent".
     *
     * This request holds the parameters needed by the the eventendpoint server.  After setting any
     * optional parameters, call the {@link InsertEvent#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertEvent#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.shawnkrecker.eventation.eventendpoint.model.Event}
     * @since 1.13
     */
    protected InsertEvent(com.shawnkrecker.eventation.eventendpoint.model.Event content) {
      super(Eventendpoint.this, "POST", REST_PATH, content, com.shawnkrecker.eventation.eventendpoint.model.Event.class);
    }

    @Override
    public InsertEvent setAlt(java.lang.String alt) {
      return (InsertEvent) super.setAlt(alt);
    }

    @Override
    public InsertEvent setFields(java.lang.String fields) {
      return (InsertEvent) super.setFields(fields);
    }

    @Override
    public InsertEvent setKey(java.lang.String key) {
      return (InsertEvent) super.setKey(key);
    }

    @Override
    public InsertEvent setOauthToken(java.lang.String oauthToken) {
      return (InsertEvent) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertEvent setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertEvent) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertEvent setQuotaUser(java.lang.String quotaUser) {
      return (InsertEvent) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertEvent setUserIp(java.lang.String userIp) {
      return (InsertEvent) super.setUserIp(userIp);
    }

    @Override
    public InsertEvent set(String parameterName, Object value) {
      return (InsertEvent) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listEvent".
   *
   * This request holds the parameters needed by the the eventendpoint server.  After setting any
   * optional parameters, call the {@link ListEvent#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListEvent listEvent() throws java.io.IOException {
    ListEvent result = new ListEvent();
    initialize(result);
    return result;
  }

  public class ListEvent extends EventendpointRequest<com.shawnkrecker.eventation.eventendpoint.model.CollectionResponseEvent> {

    private static final String REST_PATH = "event";

    /**
     * Create a request for the method "listEvent".
     *
     * This request holds the parameters needed by the the eventendpoint server.  After setting any
     * optional parameters, call the {@link ListEvent#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListEvent#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListEvent() {
      super(Eventendpoint.this, "GET", REST_PATH, null, com.shawnkrecker.eventation.eventendpoint.model.CollectionResponseEvent.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListEvent setAlt(java.lang.String alt) {
      return (ListEvent) super.setAlt(alt);
    }

    @Override
    public ListEvent setFields(java.lang.String fields) {
      return (ListEvent) super.setFields(fields);
    }

    @Override
    public ListEvent setKey(java.lang.String key) {
      return (ListEvent) super.setKey(key);
    }

    @Override
    public ListEvent setOauthToken(java.lang.String oauthToken) {
      return (ListEvent) super.setOauthToken(oauthToken);
    }

    @Override
    public ListEvent setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListEvent) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListEvent setQuotaUser(java.lang.String quotaUser) {
      return (ListEvent) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListEvent setUserIp(java.lang.String userIp) {
      return (ListEvent) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListEvent setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListEvent setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListEvent set(String parameterName, Object value) {
      return (ListEvent) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeEvent".
   *
   * This request holds the parameters needed by the the eventendpoint server.  After setting any
   * optional parameters, call the {@link RemoveEvent#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveEvent removeEvent(java.lang.Long id) throws java.io.IOException {
    RemoveEvent result = new RemoveEvent(id);
    initialize(result);
    return result;
  }

  public class RemoveEvent extends EventendpointRequest<Void> {

    private static final String REST_PATH = "event/{id}";

    /**
     * Create a request for the method "removeEvent".
     *
     * This request holds the parameters needed by the the eventendpoint server.  After setting any
     * optional parameters, call the {@link RemoveEvent#execute()} method to invoke the remote
     * operation. <p> {@link
     * RemoveEvent#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveEvent(java.lang.Long id) {
      super(Eventendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveEvent setAlt(java.lang.String alt) {
      return (RemoveEvent) super.setAlt(alt);
    }

    @Override
    public RemoveEvent setFields(java.lang.String fields) {
      return (RemoveEvent) super.setFields(fields);
    }

    @Override
    public RemoveEvent setKey(java.lang.String key) {
      return (RemoveEvent) super.setKey(key);
    }

    @Override
    public RemoveEvent setOauthToken(java.lang.String oauthToken) {
      return (RemoveEvent) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveEvent setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveEvent) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveEvent setQuotaUser(java.lang.String quotaUser) {
      return (RemoveEvent) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveEvent setUserIp(java.lang.String userIp) {
      return (RemoveEvent) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveEvent setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveEvent set(String parameterName, Object value) {
      return (RemoveEvent) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateEvent".
   *
   * This request holds the parameters needed by the the eventendpoint server.  After setting any
   * optional parameters, call the {@link UpdateEvent#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.shawnkrecker.eventation.eventendpoint.model.Event}
   * @return the request
   */
  public UpdateEvent updateEvent(com.shawnkrecker.eventation.eventendpoint.model.Event content) throws java.io.IOException {
    UpdateEvent result = new UpdateEvent(content);
    initialize(result);
    return result;
  }

  public class UpdateEvent extends EventendpointRequest<com.shawnkrecker.eventation.eventendpoint.model.Event> {

    private static final String REST_PATH = "event";

    /**
     * Create a request for the method "updateEvent".
     *
     * This request holds the parameters needed by the the eventendpoint server.  After setting any
     * optional parameters, call the {@link UpdateEvent#execute()} method to invoke the remote
     * operation. <p> {@link
     * UpdateEvent#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.shawnkrecker.eventation.eventendpoint.model.Event}
     * @since 1.13
     */
    protected UpdateEvent(com.shawnkrecker.eventation.eventendpoint.model.Event content) {
      super(Eventendpoint.this, "PUT", REST_PATH, content, com.shawnkrecker.eventation.eventendpoint.model.Event.class);
    }

    @Override
    public UpdateEvent setAlt(java.lang.String alt) {
      return (UpdateEvent) super.setAlt(alt);
    }

    @Override
    public UpdateEvent setFields(java.lang.String fields) {
      return (UpdateEvent) super.setFields(fields);
    }

    @Override
    public UpdateEvent setKey(java.lang.String key) {
      return (UpdateEvent) super.setKey(key);
    }

    @Override
    public UpdateEvent setOauthToken(java.lang.String oauthToken) {
      return (UpdateEvent) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateEvent setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateEvent) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateEvent setQuotaUser(java.lang.String quotaUser) {
      return (UpdateEvent) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateEvent setUserIp(java.lang.String userIp) {
      return (UpdateEvent) super.setUserIp(userIp);
    }

    @Override
    public UpdateEvent set(String parameterName, Object value) {
      return (UpdateEvent) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Eventendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Eventendpoint}. */
    @Override
    public Eventendpoint build() {
      return new Eventendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link EventendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setEventendpointRequestInitializer(
        EventendpointRequestInitializer eventendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(eventendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
