/*
 * Copyright 2000-2019 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.textfield.osgi;

import com.vaadin.flow.osgi.support.OsgiVaadinContributor;
import com.vaadin.flow.osgi.support.OsgiVaadinStaticResource;
import org.osgi.service.component.annotations.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Fields connector resources registration.
 *
 * @author Vaadin Ltd
 *
 */
@Component(immediate = true, service = OsgiVaadinContributor.class)
public class fieldConnectorResource implements OsgiVaadinContributor, Serializable {

    @Override
    public List<OsgiVaadinStaticResource> getContributions() {
        return Arrays.asList(
                OsgiVaadinStaticResource.create(
                        "/META-INF/resources/frontend/fieldConnector.js",
                        "/frontend/fieldConnector.js"));
    }

}