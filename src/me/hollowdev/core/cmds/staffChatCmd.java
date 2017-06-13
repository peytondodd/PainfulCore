package me.hollowdev.core.cmds;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class staffChatCmd
  implements CommandExecutor
{
  Main main;
  
  public staffChatCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player))
    {
      Utils.noPlayer();
      return true;
    }
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("sc"))
    {
      String message;
      String prefix;
      if (p.hasPermission("core.sc"))
      {
        if (args.length == 0)
        {
          p.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "Correct Usage" + Config.getBracketColor() + ": " + Config.getMainColor() + "/sc <message>"));
          return true;
        }
        message = "";
        prefix = Config.getStaffChatPrefix();
        for (int i1 = 0; i1 < args.length; i1++) {
          message = message + args[i1] + " ";
        }
        for (Player t : Bukkit.getOnlinePlayers()) {
          if (t.hasPermission("core.sc"))
          {
            message = Utils.color(" " + message);
            t.sendMessage(Utils.color(prefix + Config.getMainColor() + p.getName() + Config.getBracketColor() + ":" + Config.getOffColor() + message));
          }
        }
      }
      else
      {
        p.sendMessage(Utils.color(Config.getNoPermMessage()));
      }
    }
    return false;
  }
}
