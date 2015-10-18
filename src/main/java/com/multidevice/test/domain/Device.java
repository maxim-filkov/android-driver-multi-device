package com.multidevice.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describes Android device under test.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

  private String udid;

  private String model;

  private String brand;

  private String osVersion;

}
