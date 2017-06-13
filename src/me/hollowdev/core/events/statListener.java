package me.hollowdev.core.events;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import me.hollowdev.core.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class statListener
  implements Listener
{
  Main main;
  
  public statListener(Main main)
  {
    this.main = main;
  }
  
  @EventHandler
  public void onPlayerJoinEvent(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    File playerFile = getPlayerFile(p.getUniqueId().toString());
    FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
    if (!playerFile.exists())
    {
      try
      {
        playerData.createSection("stats");
        playerData.set("stats.logins", Integer.valueOf(1));
        playerData.set("stats.kills", Integer.valueOf(0));
        playerData.set("stats.deaths", Integer.valueOf(0));
        
        playerData.save(playerFile);
      }
      catch (IOException exception)
      {
        exception.printStackTrace();
      }
    }
    else
    {
      int amtOfLogins = playerData.getInt("stats.logins");
      try
      {
        playerData.set("stats.logins", Integer.valueOf(amtOfLogins + 1));
        playerData.save(playerFile);
      }
      catch (IOException exception)
      {
        exception.printStackTrace();
      }
    }
  }
  
  @EventHandler
  public void onPlayerDeathEvent(PlayerDeathEvent e)
  {
    Player p = e.getEntity();
    Player ent = e.getEntity().getKiller();
    File deathFile = getPlayerFile(p.getUniqueId().toString());
    FileConfiguration deathData = YamlConfiguration.loadConfiguration(deathFile);
    File killFile = getPlayerFile(ent.getUniqueId().toString());
    FileConfiguration killData = YamlConfiguration.loadConfiguration(killFile);
    int amtOfDeaths = deathData.getInt("stats.deaths");
    int amtOfKills = killData.getInt("stats.kills");
    try
    {
      deathData.set("stats.deaths", Integer.valueOf(amtOfDeaths + 1));
      deathData.save(deathFile);
      killData.set("stats.kills", Integer.valueOf(amtOfKills + 1));
      killData.save(killFile);
    }
    catch (IOException exception)
    {
      exception.printStackTrace();
    }
  }
  
  private File getPlayerFile(String uuid)
  {
    File playerFile = new File(Main.getInstance().getDataFolder() + File.separator + "stats", uuid + ".yml");
    return playerFile;
  }
}
