package com.multidevice.test.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or a list of
 * elements. Used in conjunction with {@link Page} this allows users to quickly and easily create PageObjects.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface FindBy {

  /**
   * Descriptive text to user interface controls in application using the android:contentDescription attribute. If no
   * element has a matching class attribute name, a NoSuchElementException will be raised.
   */
  String accessibility() default "";

  /**
   * Use this when you want to locate an element by class attribute name. With this strategy, the first element with the
   * matching class attribute name will be returned. If no element has a matching class attribute name, a
   * NoSuchElementException will be raised.
   */
  String className() default "";

  /**
   * Use this when you know id attribute of an element. With this strategy, the first element with the id attribute
   * value matching the location will be returned. If no element has a matching id attribute, a NoSuchElementException
   * will be raised.
   */
  String id() default "";

  /**
   * Use this when you know name attribute of an element. With this strategy, the first element with the name attribute
   * value matching the location will be returned. If no element has a matching name attribute, a NoSuchElementException
   * will be raised.
   */
  String name() default "";

  /**
   * Use this when you want to locate an element by tag name. With this strategy, the first element with the given tag
   * name will be returned. If no element has a matching tag name, a NoSuchElementException will be raised.
   */
  String tagName() default "";

  /**
   * uiAutomator locator. If no element has a matching tag name, a NoSuchElementException will be raised.
   */
  String uiAutomator() default "";

  /**
   * XPath is the language used for locating nodes in an XML document. As HTML can be an implementation of XML (XHTML),
   * Selenium users can leverage this powerful language to target elements in their web applications. XPath extends
   * beyond (as well as supporting) the simple methods of locating by id or name attributes, and opens up all sorts of
   * new possibilities such as locating the third checkbox on the page. If no element has a matching class attribute
   * name, a NoSuchElementException will be raised.
   */
  String xpath() default "";

  /**
   * Almost the same as {@link #id() id()} method. Use this when you want to locate an element by resourceId (package
   * name + identifier). Define only text for identifier, do not care about package. If no element has a matching class
   * attribute name, a NoSuchElementException will be raised.
   */
  String resourceId() default "";

  /**
   * Almost the same as {@link #accessibility() accessibility()} method. Use this when you want to locate an element by
   * content description. Define only text for content description. If no element has a matching class attribute name, a
   * NoSuchElementException will be raised.
   */
  String contentDescription() default "";

  /**
   * Use this when you want to locate an element by text. If no element has a matching class attribute name, a
   * NoSuchElementException will be raised.
   */
  String text() default "";

}
