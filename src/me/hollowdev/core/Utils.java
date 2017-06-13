package me.hollowdev.core;

import com.avaje.ebean.validation.NotNull;
import java.io.PrintStream;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.ChatColor;

public class Utils
{
  public static String color(String s)
  {
    return ChatColor.translateAlternateColorCodes('&', s);
  }
  
  @NotNull
  public static String randomLetters(int length)
  {
    String rand = RandomStringUtils.randomAlphabetic(length);
    if (rand == "") {
      return randomLetters(length);
    }
    return rand;
  }
  
  public static void noPlayer()
  {
    System.out.println(Config.getPrefix() + "This is a player only cmd.");
  }
}
