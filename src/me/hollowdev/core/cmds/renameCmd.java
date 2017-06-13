package me.hollowdev.core.cmds;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class renameCmd
  implements CommandExecutor
{
  Main main;
  
  public renameCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("rename")) && ((sender instanceof Player)))
    {
      Player p = (Player)sender;
      if ((p.getItemInHand() != null) && (!p.getItemInHand().getType().equals(Material.AIR)) && 
        (p.hasPermission("core.rename")))
      {
        if (args.length == 0)
        {
          p.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + " Example" + Config.getBracketColor() + ":" + Config.getOffColor() + " /rename &4Test"));
          return true;
        }
        if (args.length == 1)
        {
          ItemStack itemStack = p.getItemInHand();
          
          String string = args[0];
          String spacedString = string.replaceAll("_", " ");
          String colouredString = ChatColor.translateAlternateColorCodes('&', spacedString);
          
          ItemMeta itemStackMeta = itemStack.getItemMeta();
          itemStackMeta.setDisplayName(colouredString);
          
          itemStack.setItemMeta(itemStackMeta);
          
          p.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "Succesfully renamed the item in hand to" + Config.getBracketColor() + ": " + colouredString + " !"));
          
          return true;
        }
        p.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "Wrong usage." + Config.getOffColor() + "Example" + Config.getBracketColor() + ": &f/rename &4Test&7."));
        return true;
      }
    }
    return false;
  }
}
