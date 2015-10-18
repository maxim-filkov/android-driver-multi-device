package com.multidevice.test.framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.multidevice.test.domain.Device;
import com.multidevice.test.utils.AdbUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

/**
 * This class describes tests running configuration.
 *
 * @author mfilkov
 */
@Configuration
@ComponentScan(basePackages = { "com.multidevice.*" })
@PropertySource("classpath:tests.properties")
public class AcceptanceTestsConfiguration implements EnvironmentAware {

  @Autowired
  private Environment environment;

  /**
   * Creates Test Scope bean.
   *
   * @return Created Test Scope bean.
   */
  @Bean
  public TestScope testScope() {
    return new TestScope();
  }

  /**
   * Creates Custom Scope Configurer bean.
   *
   * @return Created Custom Scope Configurer bean.
   */
  @Bean
  public CustomScopeConfigurer customScopeConfigurer() {
    final CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
    final Map<String, Object> scopes = new HashMap<>();
    scopes.put("test", testScope());
    scopeConfigurer.setScopes(scopes);
    return scopeConfigurer;
  }

  /**
   * Prepares Android Driver.
   *
   * @return Android Driver.
   */
  @Bean
  @Scope("test")
  public List<AndroidDriver> androidDrivers() {
    final List<AndroidDriver> drivers = new ArrayList<>();
    try {
      final List<Device> devices = AdbUtils.getDevices();
      for (int i = 0; i < devices.size(); i++) {
        final Device device = devices.get(i);
        final DesiredCapabilities capabilities = getCapabilities();
        capabilities.setCapability("deviceName", device.getUdid());
        final AndroidDriver driver = new AndroidDriver(new URL(environment.getProperty("test.appium.url" + (i + 1))),
            capabilities);
        driver
            .manage()
            .timeouts()
            .implicitlyWait(Integer.parseInt(environment.getProperty("driver.wait.timeout")), TimeUnit.SECONDS);
        drivers.add(driver);
      }
      return drivers;
    } catch (final MalformedURLException e) {
      throw new RuntimeException("Appium URL is malformed");
    }
  }

  @Override
  public void setEnvironment(final Environment environment) {
    this.environment = environment;
  }

  private DesiredCapabilities getCapabilities() {
    final DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("newCommandTimeout", "2000000");
    capabilities.setCapability("platformVersion", environment.getProperty("test.platform.version"));
    capabilities.setCapability("appium-version", environment.getProperty("test.appium.version"));
    capabilities.setCapability("platformName", environment.getProperty("test.platform.name"));
    capabilities.setCapability("appPackage", environment.getProperty("test.application.package"));
    capabilities.setCapability("appActivity", environment.getProperty("test.application.activity"));
    return capabilities;
  }

}
