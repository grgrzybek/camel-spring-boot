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
package org.apache.camel.dataformat.bindy.springboot.number.rounding;

import java.math.BigDecimal;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.model.dataformat.BindyDataFormat;
import org.apache.camel.model.dataformat.BindyType;
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
        BindyBigDecimalRoundingUnmarshallTest.class,
        BindyBigDecimalRoundingUnmarshallTest.TestConfiguration.class
    }
)
public class BindyBigDecimalRoundingUnmarshallTest {

    
    
    private static final String URI_MOCK_RESULT = "mock:result";
    private static final String URI_DIRECT_START = "direct:start";

    @Produce(URI_DIRECT_START)
    private ProducerTemplate template;

    @EndpointInject(URI_MOCK_RESULT)
    private MockEndpoint result;

    private String record;

    @Test
    public void testBigDecimalRoundingUp() throws Exception {

        record = "'12345.789'";
        String bigDecimal = "12345.79";

        template.sendBody(record);

        result.expectedMessageCount(1);
        result.assertIsSatisfied();

        NumberModel bd = (NumberModel) result.getExchanges().get(0).getIn().getBody();
        assertEquals(bigDecimal, bd.getRoundingUp().toString());
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
                    BindyDataFormat bindy = new BindyDataFormat()
                            .type(BindyType.Csv)
                            .classType(NumberModel.class)
                            .locale("en");

                    from(URI_DIRECT_START)
                            .unmarshal(bindy)
                            .to(URI_MOCK_RESULT);
                }
            };
        }
    }
    
    @CsvRecord(separator = ",", quote = "'")
    public static class NumberModel {

        @DataField(pos = 1, precision = 2, rounding = "UP", pattern = "#####,##")
        private BigDecimal roundingUp;

        public BigDecimal getRoundingUp() {
            return roundingUp;
        }

        public void setRoundingUp(BigDecimal roundingUp) {
            this.roundingUp = roundingUp;
        }

        @Override
        public String toString() {
            return "BigDecimal rounding : " + this.roundingUp;
        }
    }
}
