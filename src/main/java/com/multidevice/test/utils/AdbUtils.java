package com.multidevice.test.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.multidevice.test.domain.Device;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class contains utilities related to ADB (Android Debug Bridge).
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdbUtils {

  private static final int COMMAND_EXECUTION_WAIT_MS = 3000;
  private static final Pattern DIMENSION_PATTERN = Pattern.compile("\\d+x\\d+");
  private static final String ADB_PATH = "/usr/local/bin/adb ";

  private static final String ADB_SCREEN_SIZE_COMMAND = "shell wm size";
  private static final String ADB_LIST_DEVICES_COMMAND = "devices";
  private static final String ADB_GET_DEVICE_MODEL_COMMAND = "shell getprop ro.product.model";
  private static final String ADB_GET_DEVICE_BRAND_COMMAND = "shell getprop ro.product.brand";
  private static final String ADB_GET_DEVICE_OS_COMMAND = "shell getprop ro.build.version.release";

  /**
   * Returns display resolution for currently attached device.
   *
   * @param deviceUDID
   *          Device UDID.
   * @return Display resolution for currently attached device.
   */
  public static Dimension getDimension(final String deviceUDID) {
    return parseDimension(CommandLineExecutor.execute(ADB_PATH + "-s " + deviceUDID + " " + ADB_SCREEN_SIZE_COMMAND,
        COMMAND_EXECUTION_WAIT_MS));
  }

  /**
   * Returns screen center position for the currently attached device.
   *
   * @param deviceUDID
   *          Device UDID.
   * @return Screen center position as a point.
   */
  public static Point getScreenCenter(final String deviceUDID) {
    final Dimension dimension = getDimension(deviceUDID);
    return new Point((int) dimension.getWidth() / 2, (int) dimension.getHeight() / 2);
  }

  /**
   * Returns a list of all connected devices (ignores unauthorized and offline ones).
   *
   * @return List of connected devices.
   */
  public static List<Device> getDevices() {
    final List<Device> devices = new ArrayList<>();
    final String output = CommandLineExecutor.execute(ADB_PATH + ADB_LIST_DEVICES_COMMAND, COMMAND_EXECUTION_WAIT_MS);
    final List<String> lines = Lists.newArrayList(output.split("\n"));
    lines.remove(0);

    for (final String line : lines) {
      if (line.contains("device")) {
        final String udid = line.replaceAll("\\s+device", "");
        final String model = execCommandForDevice(udid, ADB_GET_DEVICE_MODEL_COMMAND);
        final String brand = execCommandForDevice(udid, ADB_GET_DEVICE_BRAND_COMMAND);
        final String os = execCommandForDevice(udid, ADB_GET_DEVICE_OS_COMMAND);
        devices.add(new Device(udid, model, brand, os));
      }
    }

    return devices;
  }

  /**
   * Starts ADB server daemon.
   */
  public static void startServer() {
    CommandLineExecutor.execute(ADB_PATH + "start-server", COMMAND_EXECUTION_WAIT_MS);
  }

  private static Dimension parseDimension(final String adbOutput) {
    final List<String> result = new ArrayList<>();
    final Matcher matcher = DIMENSION_PATTERN.matcher(adbOutput);

    while (matcher.find()) {
      result.add(matcher.group(0));
    }

    final int width = Integer.parseInt(result.get(0).split("x")[0]);
    final int height = Integer.parseInt(result.get(0).split("x")[1]);
    return new Dimension(width, height);
  }

  private static String execCommandForDevice(final String deviceId, final String command) {
    return CommandLineExecutor.execute(ADB_PATH + " -s " + deviceId + " " + command, COMMAND_EXECUTION_WAIT_MS);
  }

}
