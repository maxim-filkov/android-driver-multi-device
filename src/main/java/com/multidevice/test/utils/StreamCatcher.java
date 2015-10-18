package com.multidevice.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.Getter;

/**
 * This class is needed to capture standard and error output from external command line utilities, like adb.
 */
class StreamCatcher extends Thread {
  private final InputStream is;
  private final String type;
  @Getter
  private String output = "";

  /**
   * Creates a new instance for stream catcher.
   *
   * @param is
   *          Input stream.
   * @param type
   *          Type of stream (ERROR or OUTPUT).
   */
  StreamCatcher(final InputStream is, final String type) {
    this.is = is;
    this.type = type;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    try {
      final InputStreamReader isr = new InputStreamReader(is);
      final BufferedReader br = new BufferedReader(isr);
      String line = null;
      while ((line = br.readLine()) != null) {
        System.out.println(type + "> " + line);
        output += line + "\n";
      }
    } catch (final IOException ioe) {
      ioe.printStackTrace();
    }
  }

}
