/*
 * Copyright 2000-2017 Vaadin Ltd.
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
package com.vaadin.flow.component.orderedlayout.demo;

import java.util.Optional;
import java.util.function.Consumer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.impl.ThemeListImpl;
import com.vaadin.flow.router.Route;

/**
 * View for the {@link VerticalLayout} component.
 *
 * @author Vaadin Ltd
 */
@Route("vaadin-vertical-layout")
public class VerticalLayoutView extends DemoView {

    @Override
    protected void initView() {
        createDefaultLayout();
        createLayoutWithSpacing();
        createLayoutWithDefaultAlignment();
        createLayoutWithIndividualAlignments();
        createLayoutWithExpandRatios();
        createLayoutWithCenterComponent();
    }

    private void createDefaultLayout() {
        // begin-source-example
        // source-example-heading: Default layout
        VerticalLayout layout = new VerticalLayout();
        layout.getStyle().set("border", "1px solid #9E9E9E");

        Component component1 = createComponent(1, "#78909C");
        Component component2 = createComponent(2, "#546E7A");
        Component component3 = createComponent(3, "#37474F");

        layout.add(component1, component2, component3);
        // end-source-example

        layout.setId("default-layout");

        Div themeSettings = new Div(new Label(
                "Current theme supports 'margin' and 'padding': "),
                createToggleThemeButton(layout, "margin", layout::setMargin),
                createToggleThemeButton(layout, "padding", layout::setPadding));

        addCard("Default layout", layout, themeSettings);
    }

    private void createLayoutWithSpacing() {
        // begin-source-example
        // source-example-heading: Layout with justify content
        VerticalLayout layout = new VerticalLayout();
        layout.setHeight("150px");
        layout.getStyle().set("border", "1px solid #9E9E9E");

        // the default is JustifyContentMode.START
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        Component component1 = createComponent(1, "#78909C");
        Component component2 = createComponent(2, "#546E7A");
        Component component3 = createComponent(3, "#37474F");

        layout.add(component1, component2, component3);
        // end-source-example

        component2.getElement().setProperty("innerHTML",
                "Component 2<br>With long text");
        component3.getElement().getStyle().set("fontSize", "9px");

        Div buttons = new Div();
        buttons.add(createSpacingButton(layout, "justify-content-start-button",
                JustifyContentMode.START));
        buttons.add(createSpacingButton(layout, "justify-content-end-button",
                JustifyContentMode.END));
        buttons.add(createSpacingButton(layout,
                "justify-content-between-button", JustifyContentMode.BETWEEN));
        buttons.add(createSpacingButton(layout, "justify-content-around-button",
                JustifyContentMode.AROUND));
        buttons.add(createSpacingButton(layout, "justify-content-evenly-button",
                JustifyContentMode.EVENLY));

        Div themeSettings = new Div(
                new Label("Current theme supports 'spacing': "),
                createToggleThemeButton(layout, "spacing-xs"),
                createToggleThemeButton(layout, "spacing-s"),
                createToggleThemeButton(layout, "spacing", layout::setSpacing),
                createToggleThemeButton(layout, "spacing-l"),
                createToggleThemeButton(layout, "spacing-xl")
        );

        layout.setId("layout-with-justify-content");

        addCard("Layout with justify content", layout, buttons, themeSettings);
    }

    private void createLayoutWithDefaultAlignment() {
        // begin-source-example
        // source-example-heading: Layout with general alignment
        VerticalLayout layout = new VerticalLayout();
        layout.setHeight("150px");
        layout.getStyle().set("border", "1px solid #9E9E9E");
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        // the default is Alignment.START
        layout.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        Component component1 = createComponent(1, "#78909C");
        Component component2 = createComponent(2, "#546E7A");
        Component component3 = createComponent(3, "#37474F");

        layout.add(component1, component2, component3);
        // end-source-example

        component2.getElement().setText("Component 2 with long text");
        component3.getElement().setText("C 3");

        Div buttons = new Div();
        buttons.add(createAlignmentButton(layout, "align-start-button",
                Alignment.START));
        buttons.add(createAlignmentButton(layout, "align-end-button",
                Alignment.END));
        buttons.add(createAlignmentButton(layout, "align-center-button",
                Alignment.CENTER));
        buttons.add(createAlignmentButton(layout, "align-stretch-button",
                Alignment.STRETCH));

        layout.setId("layout-with-alignment");

        addCard("Layout with general alignment", layout, buttons);
    }

    private void createLayoutWithIndividualAlignments() {
        // begin-source-example
        // source-example-heading: Layout with individual alignments
        VerticalLayout layout = new VerticalLayout();
        layout.setHeight("150px");
        layout.getStyle().set("border", "1px solid #9E9E9E");
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        Component component1 = createComponent(1, "#78909C");
        layout.setHorizontalComponentAlignment(Alignment.START, component1);

        Component component2 = createComponent(2, "#546E7A");
        layout.setHorizontalComponentAlignment(Alignment.CENTER, component2);

        Component component3 = createComponent(3, "#37474F");
        layout.setHorizontalComponentAlignment(Alignment.END, component3);

        Component component4 = createComponent(4, "#263238");
        layout.setHorizontalComponentAlignment(Alignment.STRETCH, component4);

        layout.add(component1, component2, component3, component4);
        // end-source-example

        component1.setId("start-aligned");
        component2.setId("center-aligned");
        component3.setId("end-aligned");
        component4.setId("stretch-aligned");
        layout.setId("layout-with-individual-alignments");

        addCard("Layout with individual alignments", layout);
    }

    private void createLayoutWithExpandRatios() {
        // begin-source-example
        // source-example-heading: Layout with expand ratios
        VerticalLayout layout = new VerticalLayout();
        layout.setHeight("200px");
        layout.getStyle().set("border", "1px solid #9E9E9E");
        layout.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        Component component1 = createComponent(1, "#78909C");
        layout.expand(component1);

        Component component2 = createComponent(2, "#546E7A");
        layout.setFlexGrow(2, component2);

        Component component3 = createComponent(3, "#37474F");
        layout.setFlexGrow(0.5, component3);

        layout.add(component1, component2, component3);
        // end-source-example

        component1.setId("ratio-1");
        component2.setId("ratio-2");
        component3.setId("ratio-0.5");
        layout.setId("layout-with-expand-ratios");

        addCard("Layout with expand ratios", layout);
    }

    private void createLayoutWithCenterComponent() {
        // begin-source-example
        // source-example-heading: Layout with component in the center
        VerticalLayout layout = new VerticalLayout();
        layout.setHeight("200px");
        layout.getStyle().set("border", "1px solid #9E9E9E");

        Component component = createComponent(1, "#78909C");
        layout.add(component);
        layout.setHorizontalComponentAlignment(Alignment.CENTER, component);
        layout.setJustifyContentMode(JustifyContentMode.AROUND);

        // end-source-example

        component.setId("center");
        layout.setId("layout-with-center");

        addCard("Layout with component in the center", layout);
    }

    private Component createComponent(int index, String color) {
        Div component = new Div();
        component.setText("Component " + index);
        component.getStyle().set("backgroundColor", color).set("color", "white")
                .set("padding", "5px 10px");
        return component;
    }

    private Component createAlignmentButton(VerticalLayout layout, String id,
            Alignment alignment) {
        NativeButton button = new NativeButton(alignment.name());
        button.setId(id);
        button.addClickListener(event -> layout
                .setDefaultHorizontalComponentAlignment(alignment));
        return button;
    }

    private Component createSpacingButton(VerticalLayout layout, String id,
            JustifyContentMode spacing) {
        NativeButton button = new NativeButton(spacing.name());
        button.setId(id);
        button.addClickListener(event -> layout.setJustifyContentMode(spacing));
        return button;
    }

    private NativeButton createToggleThemeButton(VerticalLayout layout,
            String themeName) {
        return createToggleThemeButton(layout, themeName,
                toggle -> layout.getThemeList().set(themeName, toggle));
    }

    private NativeButton createToggleThemeButton(VerticalLayout layout,
            String themeName, Consumer<Boolean> toggleAction) {
        NativeButton toggleButton = new NativeButton(
                String.format("Toggle %s", themeName),
                event -> toggleAction.accept(hasNoAttributeValue(
                        layout.getElement(), ThemeListImpl.THEME_ATTRIBUTE_NAME,
                        themeName)));
        toggleButton.setId(String.format("toggle-%s", themeName));
        return toggleButton;
    }

    private boolean hasNoAttributeValue(Element element, String attribute,
            String attributeValue) {
        return Optional.ofNullable(element.getAttribute(attribute))
                .map(value -> !value.contains(attributeValue)).orElse(true);
    }
}