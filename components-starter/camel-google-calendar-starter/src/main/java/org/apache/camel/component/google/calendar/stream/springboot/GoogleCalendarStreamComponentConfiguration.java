/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.google.calendar.stream.springboot;

import java.util.List;
import org.apache.camel.component.google.calendar.GoogleCalendarClientFactory;
import org.apache.camel.component.google.calendar.stream.GoogleCalendarStreamConfiguration;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Poll for changes in a Google Calendar.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@ConfigurationProperties(prefix = "camel.component.google-calendar-stream")
public class GoogleCalendarStreamComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the google-calendar-stream
     * component. This is enabled by default.
     */
    private Boolean enabled;
    /**
     * Google Calendar application name. Example would be
     * camel-google-calendar/1.0
     */
    private String applicationName;
    /**
     * Allows for bridging the consumer to the Camel routing Error Handler,
     * which mean any exceptions occurred while the consumer is trying to pickup
     * incoming messages, or the likes, will now be processed as a message and
     * handled by the routing Error Handler. By default the consumer will use
     * the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that
     * will be logged at WARN or ERROR level and ignored.
     */
    private Boolean bridgeErrorHandler = false;
    /**
     * The calendarId to be used
     */
    private String calendarId = "primary";
    /**
     * Client ID of the calendar application
     */
    private String clientId;
    /**
     * The configuration. The option is a
     * org.apache.camel.component.google.calendar.stream.GoogleCalendarStreamConfiguration type.
     */
    private GoogleCalendarStreamConfiguration configuration;
    /**
     * Take into account the lastUpdate of the last event polled as start date
     * for the next poll
     */
    private Boolean considerLastUpdate = false;
    /**
     * Consume events in the selected calendar from now on
     */
    private Boolean consumeFromNow = true;
    /**
     * Delegate for wide-domain service account
     */
    private String delegate;
    /**
     * Max results to be returned
     */
    private Integer maxResults = 10;
    /**
     * The query to execute on calendar
     */
    private String query;
    /**
     * Specifies the level of permissions you want a calendar application to
     * have to a user account. See https://developers.google.com/calendar/auth
     * for more info.
     */
    private List<String> scopes;
    /**
     * Sync events, see https://developers.google.com/calendar/v3/sync Note: not
     * compatible with: 'query' and 'considerLastUpdate' parameters
     */
    private Boolean syncFlow = false;
    /**
     * Whether autowiring is enabled. This is used for automatic autowiring
     * options (the option must be marked as autowired) by looking up in the
     * registry to find if there is a single instance of matching type, which
     * then gets configured on the component. This can be used for automatic
     * configuring JDBC data sources, JMS connection factories, AWS Clients,
     * etc.
     */
    private Boolean autowiredEnabled = true;
    /**
     * The client Factory. The option is a
     * org.apache.camel.component.google.calendar.GoogleCalendarClientFactory
     * type.
     */
    private GoogleCalendarClientFactory clientFactory;
    /**
     * Used for enabling or disabling all consumer based health checks from this
     * component
     */
    private Boolean healthCheckConsumerEnabled = true;
    /**
     * Used for enabling or disabling all producer based health checks from this
     * component. Notice: Camel has by default disabled all producer based
     * health-checks. You can turn on producer checks globally by setting
     * camel.health.producersEnabled=true.
     */
    private Boolean healthCheckProducerEnabled = true;
    /**
     * OAuth 2 access token. This typically expires after an hour so
     * refreshToken is recommended for long term usage.
     */
    private String accessToken;
    /**
     * Client secret of the calendar application
     */
    private String clientSecret;
    /**
     * The emailAddress of the Google Service Account.
     */
    private String emailAddress;
    /**
     * The name of the p12 file which has the private key to use with the Google
     * Service Account.
     */
    private String p12FileName;
    /**
     * OAuth 2 refresh token. Using this, the Google Calendar component can
     * obtain a new accessToken whenever the current one expires - a necessity
     * if the application is long-lived.
     */
    private String refreshToken;
    /**
     * Service account key in json format to authenticate an application as a
     * service account. Accept base64 adding the prefix base64:
     */
    private String serviceAccountKey;
    /**
     * The email address of the user the application is trying to impersonate in
     * the service account flow.
     */
    private String user;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Boolean getBridgeErrorHandler() {
        return bridgeErrorHandler;
    }

    public void setBridgeErrorHandler(Boolean bridgeErrorHandler) {
        this.bridgeErrorHandler = bridgeErrorHandler;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public GoogleCalendarStreamConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GoogleCalendarStreamConfiguration configuration) {
        this.configuration = configuration;
    }

    public Boolean getConsiderLastUpdate() {
        return considerLastUpdate;
    }

    public void setConsiderLastUpdate(Boolean considerLastUpdate) {
        this.considerLastUpdate = considerLastUpdate;
    }

    public Boolean getConsumeFromNow() {
        return consumeFromNow;
    }

    public void setConsumeFromNow(Boolean consumeFromNow) {
        this.consumeFromNow = consumeFromNow;
    }

    public String getDelegate() {
        return delegate;
    }

    public void setDelegate(String delegate) {
        this.delegate = delegate;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public Boolean getSyncFlow() {
        return syncFlow;
    }

    public void setSyncFlow(Boolean syncFlow) {
        this.syncFlow = syncFlow;
    }

    public Boolean getAutowiredEnabled() {
        return autowiredEnabled;
    }

    public void setAutowiredEnabled(Boolean autowiredEnabled) {
        this.autowiredEnabled = autowiredEnabled;
    }

    public GoogleCalendarClientFactory getClientFactory() {
        return clientFactory;
    }

    public void setClientFactory(GoogleCalendarClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public Boolean getHealthCheckConsumerEnabled() {
        return healthCheckConsumerEnabled;
    }

    public void setHealthCheckConsumerEnabled(Boolean healthCheckConsumerEnabled) {
        this.healthCheckConsumerEnabled = healthCheckConsumerEnabled;
    }

    public Boolean getHealthCheckProducerEnabled() {
        return healthCheckProducerEnabled;
    }

    public void setHealthCheckProducerEnabled(Boolean healthCheckProducerEnabled) {
        this.healthCheckProducerEnabled = healthCheckProducerEnabled;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getP12FileName() {
        return p12FileName;
    }

    public void setP12FileName(String p12FileName) {
        this.p12FileName = p12FileName;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getServiceAccountKey() {
        return serviceAccountKey;
    }

    public void setServiceAccountKey(String serviceAccountKey) {
        this.serviceAccountKey = serviceAccountKey;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}