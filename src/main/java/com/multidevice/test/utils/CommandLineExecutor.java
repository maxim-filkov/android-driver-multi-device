package com.multidevice.test.utils;

import java.io.IOException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class contains utilities for executing command line tools.
 *
 * @author mfilkov
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandLineExecutor {

  /**
   * Executes given command.
   *
   * @param command
   *          Command to execute.
   * @param waitMs
   *          How much to wait until the command is executed, milliseconds.
   * @return Standard output for the given command.
   */
  public static String execute(final String command, final int waitMs) {
    final ProcessBuilder pb = new ProcessBuilder((command.replaceAll("\\s+", " ")).split(" "));
    StreamCatcher errorGobbler = null;
    StreamCatcher outputGobbler = null;

    Process pc;
    try {
      pc = pb.start();
      errorGobbler = new StreamCatcher(pc.getErrorStream(), "ERROR");
      outputGobbler = new StreamCatcher(pc.getInputStream(), "OUTPUT");
      outputGobbler.start();
      errorGobbler.start();
    } catch (final IOException e) {
      throw new RuntimeException(e.getMessage());
    }
    try {
      pc.waitFor();
      Thread.sleep(waitMs);
    } catch (final InterruptedException e) {
      throw new RuntimeException(e.getMessage());
    }
    return outputGobbler.getOutput();
  }

}
