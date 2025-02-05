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
package org.apache.camel.dataformat.bindy.springboot.fix;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.bindy.annotation.KeyValuePairField;
import org.apache.camel.dataformat.bindy.annotation.Message;
import org.apache.camel.dataformat.bindy.kvp.BindyKeyValuePairDataFormat;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;


@DirtiesContext
@CamelSpringBootTest
@SpringBootTest(
    classes = {
        CamelAutoConfiguration.class,
        BindySimpleKeyValuePairUnicodeNextLineTest.class,
        BindySimpleKeyValuePairUnicodeNextLineTest.TestConfiguration.class
    }
)
public class BindySimpleKeyValuePairUnicodeNextLineTest {

    private static final String URI_MOCK_RESULT = "mock:result";
    private static final String URI_DIRECT_START = "direct:start";

    @Produce(URI_DIRECT_START)
    private ProducerTemplate template;

    @EndpointInject(URI_MOCK_RESULT)
    private MockEndpoint result;

    @Test
    public void testUnmarshallMessage() throws Exception {
        String sent = "8=FIX.4.1 37=1 38=1 40=\u0085butter";

        result.expectedMessageCount(1);

        template.sendBody(sent);

        result.assertIsSatisfied();

        UnicodeFixOrder unicodeFixOrder = result.getReceivedExchanges().get(0).getIn().getBody(UnicodeFixOrder.class);

        assertEquals("1", unicodeFixOrder.getId());
        assertEquals("butter", unicodeFixOrder.getProduct());
        assertEquals("1", unicodeFixOrder.getQuantity());
    }
    
    // *************************************
    // Config
    // *************************************

    @Configuration
    public static class TestConfiguration {

        @Bean
        public RouteBuilder routeBuilder() {
            return new RouteBuilder() {
                @Override
                public void configure() {
                    BindyKeyValuePairDataFormat kvpBindyDataFormat = new BindyKeyValuePairDataFormat(UnicodeFixOrder.class);
                    from(URI_DIRECT_START).unmarshal(kvpBindyDataFormat).to(URI_MOCK_RESULT);
                }
            };
        }
    }
    
    @Message(keyValuePairSeparator = "=", pairSeparator = " ", type = "FIX", version = "4.1")
    public static class UnicodeFixOrder {
        @KeyValuePairField(tag = 37)
        private String id;
        @KeyValuePairField(tag = 40)
        private String product;
        @KeyValuePairField(tag = 38)
        private String quantity;

        public String getId() {
            return id;
        }

        public String getProduct() {
            return product;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }

}
