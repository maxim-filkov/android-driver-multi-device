package com.multidevice.test.framework;

import java.lang.reflect.Field;

import com.multidevice.test.annotations.FindBy;
import com.multidevice.test.annotations.Page;
import com.multidevice.test.constants.LocatorType;
import com.multidevice.test.domain.Locator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * This is a bean post processor class needed for initialization. It initializes all page beans, i.e. to proxies all
 * WebElement fields in them.
 */
@Component
public class PageBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(final Object bean, final String beanName) {
    if (bean.getClass().isAnnotationPresent(Page.class)) {
      for (final Field field : bean.getClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(FindBy.class)) {
          try {
            field.setAccessible(true);
            field.set(bean, locator(field));
          } catch (final IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Unable to initialize locator: " + e.getMessage());
          }
        }
      }
    }
    return bean;
  }

  private Locator locator(final Field field) {
    Locator locator = null;
    if (StringUtils.isNotBlank(field.getAnnotation(FindBy.class).accessibility())) {
      locator = new Locator(field.getAnnotation(FindBy.class).accessibility(), LocatorType.ACCESSIBILITY);
    }
    if (StringUtils.isNotBlank(field.getAnnotation(FindBy.class).className())) {
      locator = new Locator(field.getAnnotation(FindBy.class).className(), LocatorType.CLASS_NAME);
    }
    if (StringUtils.isNotBlank(field.getAnnotation(FindBy.class).id())) {
      locator = new Locator(field.getAnnotation(FindBy.class).id(), LocatorType.ID);
    }
    if (StringUtils.isNotBlank(field.getAnnotation(FindBy.class).name())) {
      locator = new Locator(field.getAnnotation(FindBy.class).name(), LocatorType.NAME);
    }
    if (StringUtils.isNotBlank(field.getAnnotation(FindBy.class).tagName())) {
      locator = new Locator(field.getAnnotation(FindBy.class).tagName(), LocatorType.TAG_NAME);
    }
    if (StringUtils.isNotBlank(field.getAnnotation(FindBy.class).uiAutomator())) {
      locator = new Locator(field.getAnnotation(FindBy.class).uiAutomator(), LocatorType.UI_AUTOMATOR);
    }
    if (StringUtils.isNotBlank(field.getAnnotation(FindBy.class).xpath())) {
      locator = new Locator(field.getAnnotation(FindBy.class).xpath(), LocatorType.XPATH);
    }
    return locator;
  }

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
    return bean;
  }

}
