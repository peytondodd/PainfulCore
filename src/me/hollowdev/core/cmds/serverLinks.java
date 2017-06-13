package me.hollowdev.core.cmds;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class serverLinks
  implements CommandExecutor
{
  Main main;
  
  public serverLinks(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("links"))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      Player p = (Player)sender;
      p.sendMessage(Utils.color(Config.getDivider()));
      p.sendMessage(Utils.color(Config.getMainColor() + "Store" + Config.getBracketColor() + ": " + Config.getOffColor() + Config.getStoreLink()));
      p.sendMessage(Utils.color(Config.getMainColor() + "TS" + Config.getBracketColor() + ": " + Config.getOffColor() + Config.getTsLink()));
      p.sendMessage(Utils.color(Config.getMainColor() + "Website" + Config.getBracketColor() + ": " + Config.getOffColor() + Config.getWebsiteLink()));
      p.sendMessage(Utils.color(Config.getDivider()));
      p.playSound(p.getLocation(), Sound.NOTE_PLING, 10.0F, 2.0F);
    }
    return false;
  }
}
