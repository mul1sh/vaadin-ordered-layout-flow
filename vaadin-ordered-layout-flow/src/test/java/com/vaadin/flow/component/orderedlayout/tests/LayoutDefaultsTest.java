package com.vaadin.flow.component.orderedlayout.tests;

import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.junit.Assert;
import org.junit.Test;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LayoutDefaultsTest {

    @Test
    public void testHorizontalLayout_byDefault_spacingIsOn() {
        Assert.assertTrue("Spacing should be on by default",
                new HorizontalLayout().isSpacing());
        Assert.assertFalse("Padding shouldn't be on by default",
                new HorizontalLayout().isPadding());
        Assert.assertFalse("Margin shouldn't be on by default",
                new HorizontalLayout().isMargin());
    }

    @Test
    public void testVerticalLayout_byDefault_spacingAndPaddingIsOn() {
        Assert.assertTrue("Padding should be on by default",
                new VerticalLayout().isPadding());
        Assert.assertTrue("Spacing should be on by default",
                new VerticalLayout().isSpacing());
        Assert.assertFalse("Margin shouldn't be on by default",
                new VerticalLayout().isMargin());
    }

    @Test
    public void create_Layout() {
        // Just testing that creating layout actually compiles and doesn't
        // throw. Test is on purpose, so that the implementation not
        // accidentally removed.
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addClickListener(event -> {});

        FlexLayout flexLayout = new FlexLayout();
        flexLayout.addClickListener(event -> {});

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClickListener(event -> {});
    }
}
