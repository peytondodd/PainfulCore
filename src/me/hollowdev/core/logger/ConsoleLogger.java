package me.hollowdev.core.logger;

import java.io.PrintStream;

public class ConsoleLogger
{
  public static void log(String msg)
  {
    System.out.println("[ConsoleLogger] " + msg);
  }
  
  public static void warn(String msg)
  {
    log("[WARNING] " + msg);
  }
}
