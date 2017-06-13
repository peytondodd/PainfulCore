package me.hollowdev.core.events;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class chatEvents
  implements Listener
{
  Main main;
  
  public chatEvents(Main main)
  {
    this.main = main;
  }
  
  @EventHandler
  public void playerChat(AsyncPlayerChatEvent e)
  {
    if ((this.main.isChatState()) && (!e.getPlayer().hasPermission("c.bypass")))
    {
      e.setCancelled(true);
      e.getPlayer().sendMessage(Utils.color(Config.getChatLocked().replace("$locker", this.main.getDisabler())));
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerChat(AsyncPlayerChatEvent event)
  {
    Player p = event.getPlayer();
    
    String message = event.getMessage();
    
    String[] messageArgs = message.split(" ");
    String newmessage = event.getMessage();
    if (p.hasPermission("core.tag"))
    {
      String arg;
      for (String arg1 : messageArgs) {
        for (Player player : Bukkit.getOnlinePlayers()) {
          if (arg1.contains(player.getName()))
          {
            player.sendMessage(Config.getNameMention().replace("{1}", p.getName()));
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
            break;
          }
        }
      }
    }
    event.setMessage(newmessage);
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
    Player p = event.getPlayer();
    p.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "This server is currently running PainfulCore v" + Config.getMainColor() + Main.getInstance().verison));
  }
}
