package com.multidevice.test.framework;

import com.multidevice.test.utils.CommandLineExecutor;

/**
 * This class starts Appium nodes for new devices and allows to manage them.
 */
public class AppiumManager {

  public void startHubs() {

  }

  public void startNodes() {
    // check hub is running
    // get devices
    // for each device: get free port and run node
    // all paths save in config
    CommandLineExecutor
        .execute(
            "/Applications/Appium.app/Contents/Resources/node/bin/node "
                + "/Applications/Appium.app//Contents/Resources/node_modules/appium/bin/appium.js -a localhost -p 3482 --udid TA9890AMTG --command-timeout 2000000 -role node",
            10000);
    // ./node /Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js -a localhost -p 3482 --udid
    // TA9890AMTG --command-timeout 2000000 -role node
  }

}
