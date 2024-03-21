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
package org.apache.camel.component.sql;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;

import javax.sql.DataSource;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@CamelSpringBootTest
@SpringBootTest(classes = { CamelAutoConfiguration.class, SqlProducerOutputHeaderTest.class,
        SqlProducerOutputHeaderTest.TestConfiguration.class, BaseSql.TestConfiguration.class })
public class SqlProducerOutputHeaderTest extends BaseSql {

    @EndpointInject("mock:query")
    private MockEndpoint queryEndpoint;

    @Test
    public void testQueryOutputHeader() throws InterruptedException {
        queryEndpoint.expectedMessageCount(1);
        queryEndpoint.expectedHeaderReceived(SqlConstants.SQL_ROW_COUNT, 1);
        queryEndpoint.expectedHeaderReceived("MaxProjectID", 3);
        queryEndpoint.message(0).body().isEqualTo("Hi there!");

        template.requestBody("direct:query", "Hi there!");

        assertMockEndpointsSatisfied();
    }

    // *************************************
    // Config
    // *************************************

    @Configuration
    public static class TestConfiguration {

        @Bean
        public DataSource dataSource() {
            return initDb();
        }

        @Bean
        public RouteBuilder routeBuilder() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:query")
                            .to("sql:select max(id) from projects?outputType=SelectOne&outputHeader=MaxProjectID")
                            .to("mock:query");
                }
            };
        }
    }
}
