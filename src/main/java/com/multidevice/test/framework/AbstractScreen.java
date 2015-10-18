package com.multidevice.test.framework;

import java.util.ArrayList;
import java.util.List;

import com.multidevice.test.domain.Locator;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is a base class which has to be inherited by all other screens.
 */
public abstract class AbstractScreen {

  @Autowired
  private DriverManager driverManager;

  /**
   * Returns a locator which uniquely defines the page.
   *
   * @return A locator uniquely defining the page.
   */
  protected abstract Locator getIdentifier();

  /**
   * Verifies if the page is shown (active) now on the screen or not.
   *
   * @return true if screen is show at the moment, otherwise returns false.
   */
  public boolean isShown() {
    return findAllBy(getIdentifier()).size() > 0;
  }

  /**
   * Scroll forward to the element which has a description or name which exactly matches the input text. The scrolling
   * is performed on the first scrollView present on the UI.
   *
   * @param text
   *          Which will be used to locate the element to scroll to.
   * @return Element which can be found after scrolling to it.
   */
  public WebElement scrollToExact(final String text) {
    return driverManager.getDriver().scrollToExact(text);
  }

  /**
   * Convenience method for swiping across the screen.
   *
   * @param startx
   *          starting x coordinate
   * @param starty
   *          starting y coordinate
   * @param endx
   *          ending x coordinate
   * @param endy
   *          ending y coordinate
   * @param duration
   *          amount of time in milliseconds for the entire swipe action to take
   */
  public void swipe(final int startx, final int starty, final int endx, final int endy, final int duration) {
    driverManager.getDriver().swipe(startx, starty, endx, endy, duration);
  }

  /**
   * @see io.appium.java_client.android.AndroidDriver#scrollTo(String).
   *
   * @param text
   *          Which will be used to locate the element to scroll to.
   * @return Element which can be found after scrolling to it.
   */
  public WebElement scrollTo(final String text) {
    return driverManager.getDriver().scrollTo(text);
  }

  /**
   * Press and hold the at the center of an element until the contextmenu event has fired.
   *
   * @param element
   *          Element to long-press.
   */
  public void longPress(final WebElement element) {
    driverManager.getAction().longPress(element);
  }

  /**
   * Hides keyboard.
   */
  public void hideKeyboard() {
    driverManager.getDriver().navigate().back();
  }

  /**
   * Returns unique identifier for currently active device under test.
   *
   * @return Device identifier (UDID).
   */
  public String deviceUDID() {
    return driverManager.deviceUDID();
  }

  /**
   * Finds web element by the given locator.
   *
   * @param locator
   *          Locator to use for searching web element.
   * @return Found web element.
   */
  public WebElement findBy(final Locator locator) {
    WebElement element = null;
    switch (locator.getLocatorType()) {
      case ACCESSIBILITY:
        element = driverManager.getDriver().findElementByAccessibilityId(locator.getText());
        break;
      case CLASS_NAME:
        element = driverManager.getDriver().findElementByClassName(locator.getText());
        break;
      case ID:
        element = driverManager.getDriver().findElementById(locator.getText());
        break;
      case NAME:
        element = driverManager.getDriver().findElementByName(locator.getText());
        break;
      case TAG_NAME:
        element = driverManager.getDriver().findElementByTagName(locator.getText());
        break;
      case UI_AUTOMATOR:
        element = driverManager.getDriver().findElementByAndroidUIAutomator(locator.getText());
        break;
      case XPATH:
        element = driverManager.getDriver().findElementByXPath(locator.getText());
        break;
      default:
        break;
    }
    return element;
  }

  /**
   * Finds web elements by the given locator.
   *
   * @param locator
   *          Locator to use for searching web element.
   * @return Found web element.
   */
  public List<WebElement> findAllBy(final Locator locator) {
    List<WebElement> elements = new ArrayList<>();
    switch (locator.getLocatorType()) {
      case ACCESSIBILITY:
        elements = driverManager.getDriver().findElementsByAccessibilityId(locator.getText());
        break;
      case CLASS_NAME:
        elements = driverManager.getDriver().findElementsByClassName(locator.getText());
        break;
      case ID:
        elements = driverManager.getDriver().findElementsById(locator.getText());
        break;
      case NAME:
        elements = driverManager.getDriver().findElementsByName(locator.getText());
        break;
      case TAG_NAME:
        elements = driverManager.getDriver().findElementsByTagName(locator.getText());
        break;
      case UI_AUTOMATOR:
        elements = driverManager.getDriver().findElementsByAndroidUIAutomator(locator.getText());
        break;
      case XPATH:
        elements = driverManager.getDriver().findElementsByXPath(locator.getText());
        break;
      default:
        break;
    }
    return elements;
  }

  /**
   * Moves element which can be located by the given locator to the specified coordinates.
   *
   * @param locator
   *          Element locator.
   * @param x
   *          X coordinate.
   * @param y
   *          Y coordinate.
   */
  public void moveTo(final Locator locator, final int x, final int y) {
    driverManager.getAction().press(findBy(locator)).moveTo(x, y).release().perform();
  }

  /**
   * Switches to next attached device under test to continue test execution.
   */
  public void switchToNextDevice() {
    driverManager.next();
  }

  /**
   * Safely stops all connected devices under test.
   */
  public void stopDevices() {
    driverManager.quit();
  }

}
