package com.multidevice.test.framework;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * This manager controls created drivers.
 *
 * @author mfilkov
 */
@Component
public class DriverManager {

  @Resource(name = "androidDrivers")
  private List<AndroidDriver> drivers;

  private List<TouchAction> actions;

  @Getter
  private AndroidDriver driver;

  @Getter
  private TouchAction action;

  private int index = 0;

  /**
   * Safely close all drivers.
   */
  public void quit() {
    for (final AndroidDriver androidDriver : drivers) {
      androidDriver.quit();
    }
  }

  /**
   * Safely resets application for all connected devices.
   */
  public void resetApp() {
    for (final AndroidDriver androidDriver : drivers) {
      androidDriver.resetApp();
    }
  }

  /**
   * Switches to next driver (makes it active). If the latest driver is currently active, it begins from the start - the
   * very first driver is returned.
   *
   * @return New active driver.
   */
  public AndroidDriver next() {
    index = (index + 1 >= drivers.size()) ? 0 : index + 1;
    driver = drivers.get(index);
    return driver;
  }

  /**
   * Switches to exact specified driver (makes it active).
   *
   * @param number
   *          Number of driver (its index).
   * @return Wanted driver.
   */
  public AndroidDriver switchTo(final int number) {
    index = number;
    driver = drivers.get(index);
    return driver;
  }

  /**
   * Returns unique identifier for currently active device under test.
   *
   * @return Device identifier (UDID).
   */
  public String deviceUDID() {
    return driver.getCapabilities().getCapability("deviceName").toString();
  }

  @PostConstruct
  private void init() {
    if (CollectionUtils.isEmpty(drivers)) {
      return;
    }
    actions = new ArrayList<>();
    for (final AndroidDriver nextDriver : drivers) {
      actions.add(new TouchAction(nextDriver));
    }
    driver = drivers.get(0);
    action = actions.get(0);
  }

}
