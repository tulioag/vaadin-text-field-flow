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
package com.vaadin.flow.component.textfield.tests;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;

import java.util.function.Consumer;

@Route("binder-validation")
public class BinderValidationPage extends Div {

    private Binder<Bean> binder = new Binder<>(Bean.class);

    public static final String BINDER_ERROR_MSG = "binder";

    public BinderValidationPage() {
        TextField textField = new TextField();
        addComponent(textField, Bean::getString, Bean::setString,
                value -> value.length() > 2, field -> field.setMinLength(1));

        TextArea textArea = new TextArea();
        addComponent(textArea, Bean::getString, Bean::setString,
                value -> value.length() > 2, field -> field.setMinLength(1));

        PasswordField passwordField = new PasswordField();
        addComponent(passwordField, Bean::getString, Bean::setString,
                value -> value.length() > 2, field -> field.setMinLength(1));

        EmailField emailField = new EmailField();
        addComponent(emailField, Bean::getString, Bean::setString,
                value -> value.length() > 20, field -> field.setMinLength(1));

        NumberField numberField = new NumberField();
        addComponent(numberField, Bean::getNumber, Bean::setNumber,
                value -> value != null && value > 2, field -> field.setMin(1));

        binder.setBean(new Bean());
    }

    private void setInvalidIndicatorLabel(Component field) {
        Element element = field.getElement();
        element.addPropertyChangeListener("invalid", event -> {
            String label = element.getProperty("invalid", false)
                    ? "invalid"
                    : "valid";
            element.setProperty("label", label == null ? "" : label);

        });
    }

    private <C extends AbstractSinglePropertyField<C, T>, T> void addComponent(
            C field, ValueProvider<Bean, T> getter, Setter<Bean, T> setter,
            SerializablePredicate<T> binderValidator,
            Consumer<C> componentConstraintSetter) {
        componentConstraintSetter.accept(field);
        setInvalidIndicatorLabel(field);
        add(field);
        binder.forField(field).withValidator(binderValidator, BINDER_ERROR_MSG)
                .bind(getter, setter);
    }

    public static class Bean {
        private String string;
        private Double number;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public Double getNumber() {
            return number;
        }

        public void setNumber(Double number) {
            this.number = number;
        }
    }

}
