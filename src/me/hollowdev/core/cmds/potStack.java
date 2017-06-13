package me.hollowdev.core.cmds;

import java.util.HashMap;
import java.util.Map;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class potStack
  implements CommandExecutor
{
  Main main;
  
  public potStack(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("pot")) && (sender.hasPermission("core.potstack")))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      Player p = (Player)sender;
      Map<ItemStack, Integer> potionAmounts = new HashMap();
      if (!p.getInventory().contains(Material.POTION))
      {
        p.sendMessage(Config.getNoPots());
        return true;
      }
      ItemStack[] arrayOfItemStack;
      int j = (arrayOfItemStack = p.getInventory().getContents()).length;
      for (int i = 0; i < j; i++)
      {
        ItemStack item = arrayOfItemStack[i];
        if ((item != null) && 
          (item.getType().equals(Material.POTION)))
        {
          ItemStack cloned = item.clone();
          cloned.setAmount(1);
          if (potionAmounts.containsKey(cloned)) {
            potionAmounts.put(cloned, Integer.valueOf(((Integer)potionAmounts.get(cloned)).intValue() + item.getAmount()));
          } else {
            potionAmounts.put(cloned, Integer.valueOf(item.getAmount()));
          }
        }
      }
      p.getInventory().remove(Material.POTION);
      for (ItemStack key : potionAmounts.keySet())
      {
        ItemStack add = key;
        add.setAmount(((Integer)potionAmounts.get(key)).intValue());
        p.getInventory().addItem(new ItemStack[] { new ItemStack(add) });
      }
      p.sendMessage(Utils.color(Config.getOnPotMessage()));
      return true;
    }
    sender.sendMessage(Config.getNoPermMessage());
    return true;
  }
}
