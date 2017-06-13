package me.hollowdev.core.events;

import java.util.ArrayList;

import me.hollowdev.core.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class freezeListener
  implements Listener
{
  Main main;
  
  public freezeListener(Main main)
  {
    this.main = main;
  }
  
  @EventHandler
  public void onMove(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if (Main.frozen.contains(p))
    {
      e.setCancelled(true);
      return;
    }
  }
}
