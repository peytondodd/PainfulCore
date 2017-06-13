package me.hollowdev.core.events;

import me.hollowdev.core.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class potStackerListener
  implements Listener
{
  Main main;
  
  public potStackerListener(Main main)
  {
    this.main = main;
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onInventoryClick(InventoryClickEvent event)
  {
    if (((event.getWhoClicked() instanceof Player)) && (event.getInventory().getType().equals(InventoryType.BREWING)) && (!event.getCursor().getType().equals(Material.AIR)) && (event.getRawSlot() < 4))
    {
      Player p = (Player)event.getWhoClicked();
      ItemStack item = p.getItemOnCursor();
      if ((item != null) && (item.getType().equals(Material.POTION)))
      {
        int amount = item.getAmount();
        if (amount > 1)
        {
          event.getInventory().clear(event.getRawSlot());
          event.setCancelled(true);
          item.setAmount(amount);
          p.setItemOnCursor(item);
          p.updateInventory();
        }
      }
    }
  }
}
