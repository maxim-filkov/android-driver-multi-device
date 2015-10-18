package com.multidevice.test.domain;

import com.multidevice.test.constants.LocatorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This class describes locator defining mechanism of how to locate elements on application screens.
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Locator {

  private String text;

  private final LocatorType locatorType;

}
