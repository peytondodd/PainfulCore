package me.hollowdev.core.cmds;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class trashCmd
  implements CommandExecutor
{
  Main main;
  
  public trashCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (!(sender instanceof Player))
    {
      Utils.noPlayer();
      return true;
    }
    Player p = (Player)sender;
    if ((cmd.getName().equalsIgnoreCase("trash")) && (p.hasPermission("core.trash")))
    {
      Inventory inv1 = Bukkit.getServer().createInventory(p, 54, Config.getMainColor() + "Trash");
      p.openInventory(inv1);
      return true;
    }
    return false;
  }
}
