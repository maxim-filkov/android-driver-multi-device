package com.multidevice.test.constants;

/**
 * This class contains all possible types of locators used to locate UI elements in application.
 */
public enum LocatorType {

  /**
   * Descriptive text to user interface controls in application using the android:contentDescription attribute.
   */
  ACCESSIBILITY,

  /**
   * Use this when you want to locate an element by class attribute name. With this strategy, the first element with the
   * matching class attribute name will be returned.
   */
  CLASS_NAME,

  /**
   * Use this when you know id attribute of an element. With this strategy, the first element with the id attribute
   * value matching the location will be returned.
   */
  ID,

  /**
   * Use this when you know name attribute of an element. With this strategy, the first element with the name attribute
   * value matching the location will be returned.
   */
  NAME,

  /**
   * Use this when you want to locate an element by tag name. With this strategy, the first element with the given tag
   * name will be returned.
   */
  TAG_NAME,

  /**
   * UIAutomator locator type, see <a href="https://github.com/appium/java-client/issues/184" >uiAutomator</a>.
   */
  UI_AUTOMATOR,

  /**
   * XPath is the language used for locating nodes in an XML document.
   */
  XPATH;

}
