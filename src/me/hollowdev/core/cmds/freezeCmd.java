package me.hollowdev.core.cmds;

import java.util.ArrayList;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class freezeCmd
  implements CommandExecutor
{
  Main main;
  
  public freezeCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String title, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("freeze")) && (sender.hasPermission("core.freeze")))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      if (args.length > 2)
      {
        sender.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "Usage" + Config.getBracketColor() + ":" + Config.getMainColor() + " /freeze <player> (-u)"));
        return true;
      }
      if (args.length == 0)
      {
        sender.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "Usage" + Config.getBracketColor() + ":" + Config.getMainColor() + " /freeze <player> (-u)"));
        return true;
      }
      if (args.length == 1)
      {
        Player target = Bukkit.getPlayer(args[0]);
        Player s = (Player)sender;
        if (target == null)
        {
          sender.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + " Player not online"));
          return true;
        }
        Main.frozen.add(target);
        sender.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "You have frozen " + Config.getMainColor() + target.getDisplayName()));
        target.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "You have been frozen by" + Config.getBracketColor() + ": " + Config.getMainColor() + sender.getName()));
        return true;
      }
      if ((args.length == 2) && (args[1].equalsIgnoreCase("-u")))
      {
        Player target = Bukkit.getPlayer(args[0]);
        Player s = (Player)sender;
        
        Main.frozen.remove(target);
        sender.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "You have unfrozen " + Config.getMainColor() + target.getDisplayName()));
        target.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "You have been unfrozen by" + Config.getBracketColor() + ": " + Config.getMainColor() + sender.getName()));
        return true;
      }
    }
    return true;
  }
}
