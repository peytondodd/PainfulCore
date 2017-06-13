package me.hollowdev.core;

import java.util.HashMap;

import me.hollowdev.core.logger.Log;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Config
{
  private static String noPermMessage;
  private static String prefix;
  private static String mainColor;
  private static String offColor;
  private static String bracketColor;
  private static String chatLocked;
  private static String chatUnlocked;
  private static String chatCleared;
  private static String divider;
  private static String reloadMessage;
  private static String invalidArgs;
  private static HashMap<String, String> commands = new HashMap();
  private static String gambleWinMsg;
  private static String gambleLoseMsg;
  private static String storeLink;
  private static String tsLink;
  private static String websiteLink;
  private static String serverName;
  private static String noPots;
  private static String onPotMessage;
  private static String staffChatPrefix;
  private static String nameMention;
  private static String optBroadcast;
  Main plugin;
  
  public Config(Main passedplugin)
  {
    this.plugin = passedplugin;
  }
  
  public static void reloadValues(FileConfiguration config)
  {
    noPermMessage = Utils.color(config.getString("general.noPermMessage"));
    prefix = Utils.color(config.getString("general.prefix"));
    mainColor = Utils.color(config.getString("general.mainColor"));
    offColor = Utils.color(config.getString("general.offColor"));
    bracketColor = Utils.color(config.getString("general.bracketColor"));
    chatLocked = Utils.color(config.getString("chatManagement.locked"));
    chatUnlocked = Utils.color(config.getString("chatManagement.unlocked"));
    chatCleared = Utils.color(config.getString("chatManagement.cleared"));
    divider = Utils.color(config.getString("general.divider"));
    reloadMessage = Utils.color(config.getString("general.reloadMessage"));
    invalidArgs = Utils.color(config.getString("general.invalidArgs"));
    gambleWinMsg = Utils.color(config.getString("gamble.winMessage"));
    gambleLoseMsg = Utils.color(config.getString("gamble.loseMessage"));
    storeLink = Utils.color(config.getString("links.storeLink"));
    tsLink = Utils.color(config.getString("links.tsLink"));
    websiteLink = Utils.color(config.getString("links.websiteLink"));
    serverName = Utils.color(config.getString("general.serverName"));
    noPots = Utils.color(config.getString("potStacker.noPots"));
    onPotMessage = Utils.color(config.getString("potStacker.onPotMessage"));
    staffChatPrefix = Utils.color(config.getString("general.staffChatPrefix"));
    nameMention = Utils.color(config.getString("general.nameMention"));
    optBroadcast = Utils.color(config.getString("general.optBroadcast"));
    
    loadCommands(config);
    Log.logAll("[Core] Config loaded!");
  }
  
  public static void loadCommands(FileConfiguration config)
  {
    commands.clear();
    if (config.contains("commands")) {
      for (String command : config.getConfigurationSection("commands").getKeys(false))
      {
        String message = Utils.color(config.getString("commands." + command, ""));
        if (!message.isEmpty()) {
          commands.put(command.toLowerCase(), message);
        }
      }
    }
  }
  
  public static String getNoPermMessage()
  {
    return noPermMessage;
  }
  
  public static String getPrefix()
  {
    return prefix;
  }
  
  public static String getMainColor()
  {
    return mainColor;
  }
  
  public static String getOffColor()
  {
    return offColor;
  }
  
  public static String getBracketColor()
  {
    return bracketColor;
  }
  
  public static String getChatLocked()
  {
    return chatLocked;
  }
  
  public static String getChatUnlocked()
  {
    return chatUnlocked;
  }
  
  public static String getChatCleared()
  {
    return chatCleared;
  }
  
  public static String getDivider()
  {
    return divider;
  }
  
  public static HashMap<String, String> getCommands()
  {
    return commands;
  }
  
  public static String getReloadMessage()
  {
    return reloadMessage;
  }
  
  public static String getInvalidArgs()
  {
    return invalidArgs;
  }
  
  public static String getGambleWinMsg()
  {
    return gambleWinMsg;
  }
  
  public static String getGambleLoseMsg()
  {
    return gambleLoseMsg;
  }
  
  public static String getStoreLink()
  {
    return storeLink;
  }
  
  public static String getTsLink()
  {
    return tsLink;
  }
  
  public static String getWebsiteLink()
  {
    return websiteLink;
  }
  
  public static String getServerName()
  {
    return serverName;
  }
  
  public static String getNoPots()
  {
    return noPots;
  }
  
  public static String getOnPotMessage()
  {
    return onPotMessage;
  }
  
  public static String getStaffChatPrefix()
  {
    return staffChatPrefix;
  }
  
  public static String getNameMention()
  {
    return nameMention;
  }
  
  public static String getOptBroadcast()
  {
    return optBroadcast;
  }
}
