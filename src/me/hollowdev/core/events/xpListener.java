package me.hollowdev.core.events;

import me.hollowdev.core.Main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class xpListener
  implements Listener
{
  Main main;
  
  public xpListener(Main main)
  {
    this.main = main;
  }
  
  @EventHandler
  public void onBlockBreak(BlockBreakEvent e)
  {
    Player p = e.getPlayer();
    int addxp = e.getExpToDrop();
    e.setExpToDrop(0);
    p.giveExp(addxp);
  }
  
  @EventHandler
  public void onEntityDeath(EntityDeathEvent e)
  {
    Entity killer = e.getEntity().getKiller();
    if ((killer instanceof Player))
    {
      Player kill = (Player)killer;
      kill.giveExp(e.getDroppedExp());
      e.setDroppedExp(0);
    }
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent e)
  {
    Entity player = e.getEntity();
    Entity killer = e.getEntity().getKiller();
    if (((player instanceof Player)) && ((killer instanceof Player)))
    {
      Player k = (Player)killer;
      k.giveExp(e.getDroppedExp());
      e.setDroppedExp(0);
    }
  }
}
