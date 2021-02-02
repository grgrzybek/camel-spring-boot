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
package org.apache.camel.component.springrabbit.springboot;

import javax.annotation.Generated;
import org.apache.camel.component.springrabbit.ListenerContainerFactory;
import org.apache.camel.component.springrabbit.MessagePropertiesConverter;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ErrorHandler;

/**
 * Send and receive messages from RabbitMQ using Spring RabbitMQ client.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.spring-rabbitmq")
public class SpringRabbitMQComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the spring-rabbitmq component.
     * This is enabled by default.
     */
    private Boolean enabled;
    /**
     * Optional AMQP Admin service to use for auto declaring elements (queues,
     * exchanges, bindings). The option is a
     * org.springframework.amqp.core.AmqpAdmin type.
     */
    private AmqpAdmin amqpAdmin;
    /**
     * The connection factory to be use. A connection factory must be configured
     * either on the component or endpoint. The option is a
     * org.springframework.amqp.rabbit.connection.ConnectionFactory type.
     */
    private ConnectionFactory connectionFactory;
    /**
     * Specifies whether to test the connection on startup. This ensures that
     * when Camel starts that all the JMS consumers have a valid connection to
     * the JMS broker. If a connection cannot be granted then Camel throws an
     * exception on startup. This ensures that Camel is not started with failed
     * connections. The JMS producers is tested as well.
     */
    private Boolean testConnectionOnStartup = false;
    /**
     * Specifies whether the consumer should auto declare binding between
     * exchange, queue and routing key when starting. Enabling this can be good
     * for development to make it easy to standup exchanges, queues and bindings
     * on the broker.
     */
    private Boolean autoDeclare = false;
    /**
     * Specifies whether the consumer container should auto-startup.
     */
    private Boolean autoStartup = true;
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
     * The name of the dead letter exchange
     */
    private String deadLetterExchange;
    /**
     * The type of the dead letter exchange
     */
    private String deadLetterExchangeType = "direct";
    /**
     * The name of the dead letter queue
     */
    private String deadLetterQueue;
    /**
     * The routing key for the dead letter exchange
     */
    private String deadLetterRoutingKey;
    /**
     * To use a custom ErrorHandler for handling exceptions from the message
     * listener (consumer). The option is a
     * org.springframework.util.ErrorHandler type.
     */
    private ErrorHandler errorHandler;
    /**
     * To use a custom factory for creating and configuring ListenerContainer to
     * be used by the consumer for receiving messages. The option is a
     * org.apache.camel.component.springrabbit.ListenerContainerFactory type.
     */
    private ListenerContainerFactory listenerContainerFactory;
    /**
     * Tell the broker how many messages to send to each consumer in a single
     * request. Often this can be set quite high to improve throughput.
     */
    private Integer prefetchCount = 250;
    /**
     * The time to wait for workers in milliseconds after the container is
     * stopped. If any workers are active when the shutdown signal comes they
     * will be allowed to finish processing as long as they can finish within
     * this timeout. The option is a long type.
     */
    private Long shutdownTimeout = 5000L;
    /**
     * Whether the producer should be started lazy (on the first message). By
     * starting lazy you can use this to allow CamelContext and routes to
     * startup in situations where a producer may otherwise fail during starting
     * and cause the route to fail being started. By deferring this startup to
     * be lazy then the startup failure can be handled during routing messages
     * via Camel's routing error handlers. Beware that when the first message is
     * processed then creating and starting the producer may take a little time
     * and prolong the total processing time of the processing.
     */
    private Boolean lazyStartProducer = false;
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
     * Switch on ignore exceptions such as mismatched properties when declaring
     */
    private Boolean ignoreDeclarationExceptions = false;
    /**
     * To use a custom MessageConverter so you can be in control how to map
     * to/from a org.springframework.amqp.core.Message. The option is a
     * org.springframework.amqp.support.converter.MessageConverter type.
     */
    private MessageConverter messageConverter;
    /**
     * To use a custom MessagePropertiesConverter so you can be in control how
     * to map to/from a org.springframework.amqp.core.MessageProperties. The
     * option is a
     * org.apache.camel.component.springrabbit.MessagePropertiesConverter type.
     */
    private MessagePropertiesConverter messagePropertiesConverter;
    /**
     * To use a custom org.apache.camel.spi.HeaderFilterStrategy to filter
     * header to and from Camel message. The option is a
     * org.apache.camel.spi.HeaderFilterStrategy type.
     */
    private HeaderFilterStrategy headerFilterStrategy;

    public AmqpAdmin getAmqpAdmin() {
        return amqpAdmin;
    }

    public void setAmqpAdmin(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Boolean getTestConnectionOnStartup() {
        return testConnectionOnStartup;
    }

    public void setTestConnectionOnStartup(Boolean testConnectionOnStartup) {
        this.testConnectionOnStartup = testConnectionOnStartup;
    }

    public Boolean getAutoDeclare() {
        return autoDeclare;
    }

    public void setAutoDeclare(Boolean autoDeclare) {
        this.autoDeclare = autoDeclare;
    }

    public Boolean getAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(Boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

    public Boolean getBridgeErrorHandler() {
        return bridgeErrorHandler;
    }

    public void setBridgeErrorHandler(Boolean bridgeErrorHandler) {
        this.bridgeErrorHandler = bridgeErrorHandler;
    }

    public String getDeadLetterExchange() {
        return deadLetterExchange;
    }

    public void setDeadLetterExchange(String deadLetterExchange) {
        this.deadLetterExchange = deadLetterExchange;
    }

    public String getDeadLetterExchangeType() {
        return deadLetterExchangeType;
    }

    public void setDeadLetterExchangeType(String deadLetterExchangeType) {
        this.deadLetterExchangeType = deadLetterExchangeType;
    }

    public String getDeadLetterQueue() {
        return deadLetterQueue;
    }

    public void setDeadLetterQueue(String deadLetterQueue) {
        this.deadLetterQueue = deadLetterQueue;
    }

    public String getDeadLetterRoutingKey() {
        return deadLetterRoutingKey;
    }

    public void setDeadLetterRoutingKey(String deadLetterRoutingKey) {
        this.deadLetterRoutingKey = deadLetterRoutingKey;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public ListenerContainerFactory getListenerContainerFactory() {
        return listenerContainerFactory;
    }

    public void setListenerContainerFactory(
            ListenerContainerFactory listenerContainerFactory) {
        this.listenerContainerFactory = listenerContainerFactory;
    }

    public Integer getPrefetchCount() {
        return prefetchCount;
    }

    public void setPrefetchCount(Integer prefetchCount) {
        this.prefetchCount = prefetchCount;
    }

    public Long getShutdownTimeout() {
        return shutdownTimeout;
    }

    public void setShutdownTimeout(Long shutdownTimeout) {
        this.shutdownTimeout = shutdownTimeout;
    }

    public Boolean getLazyStartProducer() {
        return lazyStartProducer;
    }

    public void setLazyStartProducer(Boolean lazyStartProducer) {
        this.lazyStartProducer = lazyStartProducer;
    }

    public Boolean getAutowiredEnabled() {
        return autowiredEnabled;
    }

    public void setAutowiredEnabled(Boolean autowiredEnabled) {
        this.autowiredEnabled = autowiredEnabled;
    }

    public Boolean getIgnoreDeclarationExceptions() {
        return ignoreDeclarationExceptions;
    }

    public void setIgnoreDeclarationExceptions(
            Boolean ignoreDeclarationExceptions) {
        this.ignoreDeclarationExceptions = ignoreDeclarationExceptions;
    }

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public MessagePropertiesConverter getMessagePropertiesConverter() {
        return messagePropertiesConverter;
    }

    public void setMessagePropertiesConverter(
            MessagePropertiesConverter messagePropertiesConverter) {
        this.messagePropertiesConverter = messagePropertiesConverter;
    }

    public HeaderFilterStrategy getHeaderFilterStrategy() {
        return headerFilterStrategy;
    }

    public void setHeaderFilterStrategy(
            HeaderFilterStrategy headerFilterStrategy) {
        this.headerFilterStrategy = headerFilterStrategy;
    }
}