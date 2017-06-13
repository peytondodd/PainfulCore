package me.hollowdev.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.hollowdev.core.chatManagement.Chat;
import me.hollowdev.core.cmds.freezeCmd;
import me.hollowdev.core.cmds.gambleCmd;
import me.hollowdev.core.cmds.getStaffCmd;
import me.hollowdev.core.cmds.helpCmd;
import me.hollowdev.core.cmds.nvCmd;
import me.hollowdev.core.cmds.potStack;
import me.hollowdev.core.cmds.renameCmd;
import me.hollowdev.core.cmds.serverLinks;
import me.hollowdev.core.cmds.staffChatCmd;
import me.hollowdev.core.cmds.statsCmd;
import me.hollowdev.core.cmds.trashCmd;
import me.hollowdev.core.commandBlock.cmdBlock;
import me.hollowdev.core.events.chatEvents;
import me.hollowdev.core.events.freezeListener;
import me.hollowdev.core.events.lagListener;
import me.hollowdev.core.events.potStackerListener;
import me.hollowdev.core.events.statListener;
import me.hollowdev.core.events.xpListener;
import me.hollowdev.core.logger.FileLogger;
import me.hollowdev.core.logger.Log;
import me.hollowdev.core.monitor.monitorCmd;
import me.hollowdev.core.serverList.listCmd;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main
  extends JavaPlugin
{
  public static FileLogger logger;
  public static long serverStart;
  public static ArrayList<Player> frozen = new ArrayList();
  private static Economy economy = null;
  private static Main instance;
  public String verison = getDescription().getVersion();
  public List<String> NV = new ArrayList();
  private String disabler = "CONSOLE";
  private boolean chatState = false;
  
  public Main()
  {
    instance = this;
  }
  
  public static Main getInstance()
  {
    return instance;
  }
  
  public void onEnable()
  {
    try
    {
      instance = this;
      ArrayList plugins = new ArrayList();
      if (!getDataFolder().exists()) {
        getDataFolder().mkdir();
      }
      File folder = new File(getDataFolder(), "log");
      if (!folder.exists()) {
        folder.mkdir();
      }
      logger = new FileLogger(folder);
      File f = new File(getDataFolder(), "data.yml");
      if (!f.exists()) {
        f.createNewFile();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    loadConfiguration();
    saveConfig();
    reloadConfig();
    setupEconomy();
    Commands();
    Listeners();
    removeRecipe(Material.HOPPER);
    serverStart = System.currentTimeMillis();
    Log.logAll("[Core] has been enabled v." + getDescription().getVersion());
  }
  
  private void Commands()
  {
    getCommand("c").setExecutor(new Chat(this));
    getCommand("gamble").setExecutor(new gambleCmd(this));
    getCommand("links").setExecutor(new serverLinks(this));
    getCommand("list").setExecutor(new listCmd(this));
    getCommand("monitor").setExecutor(new monitorCmd(this));
    getCommand("pot").setExecutor(new potStack(this));
    getCommand("trash").setExecutor(new trashCmd(this));
    getCommand("core").setExecutor(new helpCmd(this));
    getCommand("getstaff").setExecutor(new getStaffCmd(this));
    getCommand("sc").setExecutor(new staffChatCmd(this));
    getCommand("freeze").setExecutor(new freezeCmd(this));
    getCommand("rename").setExecutor(new renameCmd(this));
    getCommand("nv").setExecutor(new nvCmd(this));
    getCommand("pstats").setExecutor(new statsCmd(this));
    
    Log.logAll("[Core] Commands loaded!");
  }
  
  private void Listeners()
  {
    PluginManager pm = Bukkit.getPluginManager();
    pm.registerEvents(new chatEvents(this), this);
    pm.registerEvents(new cmdBlock(this), this);
    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new lagListener(this), 100L, 1L);
    pm.registerEvents(new potStackerListener(this), this);
    pm.registerEvents(new statListener(this), this);
    pm.registerEvents(new freezeListener(this), this);
    pm.registerEvents(new xpListener(this), this);
    
    Log.logAll("[Core] Listeners loaded!");
  }
  
  private void setupEconomy()
  {
    RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
    if (economyProvider != null) {
      economy = (Economy)economyProvider.getProvider();
    }
    Log.logAll("[Core] Vault hooked!");
  }
  
  private void loadConfiguration()
  {
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
    saveConfig();
    
    Log.logAll("[Core] Configuration loaded!");
  }
  
  public void reloadConfig()
  {
    super.reloadConfig();
    Config.reloadValues(getConfig());
  }
  
  public boolean isChatState()
  {
    return this.chatState;
  }
  
  public void setChatState(boolean chatState)
  {
    this.chatState = chatState;
  }
  
  public String getDisabler()
  {
    return this.disabler;
  }
  
  public void setDisabler(String disabler)
  {
    this.disabler = disabler;
  }
  
  public Economy getEconomy()
  {
    return economy;
  }
  
  public void removeRecipe(Material material)
  {
    Iterator<Recipe> it = Bukkit.recipeIterator();
    while (it.hasNext()) {
      if (((Recipe)it.next()).getResult().getType() == material) {
        it.remove();
      }
    }
  }
  
  public String getUptime()
  {
    long now = System.currentTimeMillis();
    long diff = now - serverStart;
    String msg = Config.getMainColor() + (int)(diff / 86400000L) + Config.getOffColor() + " day(s) " + Config.getMainColor() + (int)(diff / 3600000L % 24L) + Config.getOffColor() + " hour(s) " + Config.getMainColor() + (int)(diff / 60000L % 60L) + Config.getOffColor() + " minutes(s)";
    return msg;
  }
}
