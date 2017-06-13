package me.hollowdev.core.logger;

import me.hollowdev.core.Main;

public class Log
{
  public static void logAll(String msg)
  {
    Main.logger.log(msg);
    ConsoleLogger.log(msg);
  }
  
  public static void warnAll(String msg)
  {
    Main.logger.warn(msg);
    ConsoleLogger.warn(msg);
  }
  
  public static void logToFile(String msg)
  {
    Main.logger.log(msg);
  }
  
  public static void warnToFile(String msg)
  {
    Main.logger.warn(msg);
  }
}
