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
package com.vaadin.flow.component.orderedlayout.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.orderedlayout.demo.HorizontalLayoutView;
import com.vaadin.flow.demo.ComponentDemoTest;

/**
 * Tests for the {@link HorizontalLayoutView}.
 */
public class HorizontalLayoutViewIT extends ComponentDemoTest {
    @Override
    protected String getTestPath() {
        return "/vaadin-horizontal-layout";
    }

    @Test
    public void defaultLayout() {
        WebElement vlayout = layout.findElement(By.id("default-layout"));
        assertBasicFlexPropertiesAreSet(vlayout);

        checkThemeChanges(vlayout, "margin", true);
        checkThemeChanges(vlayout, "padding", true);

        Assert.assertTrue(
                "After turning on both margin and padding, layout should contain both themes in 'theme' attribute",
                vlayout.getAttribute("theme").contains("margin")
                        && layout.findElement(By.id("default-layout"))
                        .getAttribute("theme").contains("padding"));

        checkThemeChanges(vlayout, "margin", false);
        checkThemeChanges(vlayout, "padding", false);

        Assert.assertNull(
                "After turning on both margin and padding, layout should not contain 'theme' attribute",
                vlayout.getAttribute("theme"));
    }

    @Test
    public void layoutWithJustifyContent() {
        WebElement vlayout = layout
                .findElement(By.id("layout-with-justify-content"));
        assertBasicFlexPropertiesAreSet(vlayout);

        Assert.assertEquals("space-between",
                vlayout.getCssValue("justify-content"));

        WebElement button = layout
                .findElement(By.id("justify-content-start-button"));
        scrollIntoViewAndClick(button);
        waitUntil(driver -> "flex-start"
                .equals(vlayout.getCssValue("justify-content")));

        button = layout.findElement(By.id("justify-content-end-button"));
        scrollIntoViewAndClick(button);
        waitUntil(driver -> "flex-end"
                .equals(vlayout.getCssValue("justify-content")));

        button = layout.findElement(By.id("justify-content-between-button"));
        scrollIntoViewAndClick(button);
        waitUntil(driver -> "space-between"
                .equals(vlayout.getCssValue("justify-content")));

        button = layout.findElement(By.id("justify-content-around-button"));
        scrollIntoViewAndClick(button);
        waitUntil(driver -> "space-around"
                .equals(vlayout.getCssValue("justify-content")));

        button = layout.findElement(By.id("justify-content-evenly-button"));
        scrollIntoViewAndClick(button);
        waitUntil(driver -> "space-evenly"
                .equals(vlayout.getCssValue("justify-content")));

        checkThemeChanges(vlayout, "spacing-xs", true);
        checkThemeChanges(vlayout, "spacing-s", true);
        checkThemeChanges(vlayout, "spacing", true);
        checkThemeChanges(vlayout, "spacing-l", true);
        checkThemeChanges(vlayout, "spacing-xl", true);
    }

    @Test
    public void layoutWithAlignment() {
        WebElement vlayout = layout.findElement(By.id("layout-with-alignment"));
        assertBasicFlexPropertiesAreSet(vlayout);

        Assert.assertEquals("center", vlayout.getCssValue("align-items"));

        WebElement button = layout.findElement(By.id("align-end-button"));
        scrollIntoViewAndClick(button);
        waitUntil(driver -> "flex-end"
                .equals(vlayout.getCssValue("align-items")));

        button = layout.findElement(By.id("align-center-button"));
        scrollIntoViewAndClick(button);
        waitUntil(
                driver -> "center".equals(vlayout.getCssValue("align-items")));

        button = layout.findElement(By.id("align-stretch-button"));
        scrollIntoViewAndClick(button);
        waitUntil(
                driver -> "stretch".equals(vlayout.getCssValue("align-items")));

        button = layout.findElement(By.id("align-start-button"));
        button.click();
        waitUntil(driver -> "flex-start"
                .equals(vlayout.getCssValue("align-items")));

        button = layout.findElement(By.id("align-baseline-button"));
        button.click();
        waitUntil(driver -> "baseline"
                .equals(vlayout.getCssValue("align-items")));
    }

    @Test
    public void layoutWithIndividualAlignments() {
        WebElement vlayout = layout
                .findElement(By.id("layout-with-individual-alignments"));
        assertBasicFlexPropertiesAreSet(vlayout);

        Assert.assertEquals("space-between",
                vlayout.getCssValue("justify-content"));

        WebElement child = vlayout.findElement(By.id("start-aligned"));
        Assert.assertEquals("flex-start", child.getCssValue("align-self"));

        child = vlayout.findElement(By.id("center-aligned"));
        Assert.assertEquals("center", child.getCssValue("align-self"));

        child = vlayout.findElement(By.id("end-aligned"));
        Assert.assertEquals("flex-end", child.getCssValue("align-self"));

        child = vlayout.findElement(By.id("stretch-aligned"));
        Assert.assertEquals("stretch", child.getCssValue("align-self"));
    }

    @Test
    public void layoutWithExpandRatios() {
        WebElement vlayout = layout
                .findElement(By.id("layout-with-expand-ratios"));
        assertBasicFlexPropertiesAreSet(vlayout);

        WebElement child = vlayout.findElement(By.id("ratio-1"));
        Assert.assertEquals("1", child.getCssValue("flex-grow"));

        child = vlayout.findElement(By.id("ratio-2"));
        Assert.assertEquals("2", child.getCssValue("flex-grow"));

        child = vlayout.findElement(By.id("ratio-0.5"));
        Assert.assertEquals("0.5", child.getCssValue("flex-grow"));
    }

    @Test
    public void centerComponent() {
        WebElement hlayout = layout.findElement(By.id("layout-with-center"));
        assertBasicFlexPropertiesAreSet(hlayout);

        Assert.assertEquals("space-around",
                hlayout.getCssValue("justify-content"));

        Assert.assertTrue(isElementPresent(By.id("center")));
    }

    private void assertBasicFlexPropertiesAreSet(WebElement vlayout) {
        Assert.assertEquals("row", vlayout.getCssValue("flex-direction"));
    }

    private void checkThemeChanges(WebElement layoutToCheck, String themeName, boolean shouldPresent) {
        layout.findElement(By.id(String.format("toggle-%s", themeName))).click();
        if (shouldPresent) {
            waitUntil(dr -> layoutToCheck.getAttribute("theme") != null
                    && layoutToCheck.getAttribute("theme").contains(themeName));
        } else {
            waitUntil(dr -> layoutToCheck.getAttribute("theme") == null
                    || !layoutToCheck.getAttribute("theme").contains(themeName));
        }
    }
}