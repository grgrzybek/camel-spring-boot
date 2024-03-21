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
package org.apache.camel.component.jsonpath.springboot.test;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileConsumer;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;

@DirtiesContext
@CamelSpringBootTest
@SpringBootTest(classes = { CamelAutoConfiguration.class, JsonPathSourceTest.class,
        JsonPathSourceTest.TestConfiguration.class })
public class JsonPathSourceTest {

    private static final String MESSAGE1 = "Joseph und seine Br\u00fcder";
    private static final String MESSAGE2 = "G\u00f6tzend\u00e4mmerung";
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

    @AfterAll
    public static void setDefaultCharsetBack() {
        switchToDefaultCharset(DEFAULT_CHARSET.displayName());
    }

    @Autowired
    ProducerTemplate template;

    @EndpointInject("mock:title")
    MockEndpoint mock;

    @Test
    public void testPriceResultTypeOnGenericFileUTF8() throws Exception {
        switchToDefaultCharset("UTF-8");
        mock.reset();
        mock.expectedMessageCount(2);
        mock.message(0).body().isEqualTo(MESSAGE1);
        mock.message(1).body().isEqualTo(MESSAGE2);

        template.sendBody("direct:start", FileConsumer.asGenericFile("src/test/resources/germanbooks-utf8.json",
                new File("src/test/resources/germanbooks-utf8.json"), "UTF-8", false));
        template.sendBody("direct:second", FileConsumer.asGenericFile("src/test/resources/germanbooks-utf8.json",
                new File("src/test/resources/germanbooks-utf8.json"), "UTF-8", false));

        mock.assertIsSatisfied();
    }

    @Test
    public void testPriceResultTypeOnGenericFileUTF8OnWindows() throws Exception {
        switchToDefaultCharset("windows-1252");
        mock.reset();
        mock.expectedMessageCount(2);
        mock.message(0).body().isEqualTo(MESSAGE1);
        mock.message(1).body().isEqualTo(MESSAGE2);

        template.sendBody("direct:start", FileConsumer.asGenericFile("src/test/resources/germanbooks-utf8.json",
                new File("src/test/resources/germanbooks-utf8.json"), "UTF-8", false));
        template.sendBody("direct:second", FileConsumer.asGenericFile("src/test/resources/germanbooks-utf8.json",
                new File("src/test/resources/germanbooks-utf8.json"), "UTF-8", false));

        mock.assertIsSatisfied();
    }

    @Test
    public void testPriceResultTypeOnGenericFileISO88591() throws Exception {
        switchToDefaultCharset("ISO-8859-1");
        mock.reset();
        mock.expectedMessageCount(2);
        mock.message(0).body().isEqualTo(MESSAGE1);
        mock.message(1).body().isEqualTo(MESSAGE2);

        template.sendBody("direct:start", FileConsumer.asGenericFile("src/test/resources/germanbooks-iso-8859-1.json",
                new File("src/test/resources/germanbooks-iso-8859-1.json"), "ISO-8859-1", false));
        template.sendBody("direct:second", FileConsumer.asGenericFile("src/test/resources/germanbooks-iso-8859-1.json",
                new File("src/test/resources/germanbooks-iso-8859-1.json"), "ISO-8859-1", false));

        mock.assertIsSatisfied();
    }

    private static void switchToDefaultCharset(String charset) {
        try {
            Field defaultCharset = Charset.class.getDeclaredField("defaultCharset");
            defaultCharset.setAccessible(true);
            defaultCharset.set(null, Charset.forName(charset));
        } catch (Exception e) {
            // Do nothing here
        }
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
                public void configure() throws Exception {
                    from("direct:start").transform().jsonpath("$.store.book[0].title", String.class).to("mock:title");

                    from("direct:second").transform().jsonpath("$.store.book[1].title", String.class).to("mock:title");
                }
            };
        }
    }
}
