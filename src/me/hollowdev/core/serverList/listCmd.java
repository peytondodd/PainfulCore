package me.hollowdev.core.serverList;

import java.util.Collection;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class listCmd
  implements CommandExecutor
{
  Main main;
  
  public listCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p;
    String staff;
    int players;
    int donor;
    if (cmd.getName().equalsIgnoreCase("list"))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      p = (Player)sender;
      staff = Utils.color(Config.getMainColor() + "None");
      players = Bukkit.getOnlinePlayers().size();
      donor = 0;
      for (Player pl : Bukkit.getOnlinePlayers())
      {
        if (pl.hasPermission("core.list.staff"))
        {
          players--;
          donor--;
        }
        if (pl.hasPermission("core.list.donor")) {
          donor++;
        }
        if (pl.hasPermission("core.list.staff")) {
          if (staff.equalsIgnoreCase(Utils.color(Config.getMainColor() + "None"))) {
            staff = Config.getMainColor() + pl.getName() + "&8, ";
          } else {
            staff = staff + Config.getMainColor() + pl.getName() + "&8, ";
          }
        }
        staff = staff.trim();
        if (staff.endsWith(",")) {
          staff = staff.substring(0, staff.length() - 1);
        }
        p.sendMessage(Utils.color(Config.getDivider()));
        p.sendMessage(Utils.color(Config.getOffColor() + "Online Players " + Config.getBracketColor() + "> " + Config.getMainColor() + players));
        p.sendMessage(Utils.color(Config.getOffColor() + "Online Donors " + Config.getBracketColor() + "> " + Config.getMainColor() + donor));
        p.sendMessage(Utils.color(Config.getOffColor() + "Online Staff " + Config.getBracketColor() + "> " + Config.getMainColor() + staff));
        p.sendMessage(Utils.color(Config.getDivider()));
      }
    }
    return false;
  }
}
