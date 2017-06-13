package me.hollowdev.core.cmds;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpCmd
  implements CommandExecutor
{
  Main main;
  
  public helpCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("core"))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      Player p = (Player)sender;
      if (args.length < 1)
      {
        p.sendMessage(Config.getDivider());
        p.sendMessage(Config.getPrefix());
        p.sendMessage(Utils.color(Config.getOffColor() + "PainfulCore v1.0 Developed by HollowDev"));
        p.sendMessage(Utils.color(Config.getOffColor() + "http://HollowDev.me"));
        p.sendMessage(Config.getDivider());
      }
      if (args.length == 1)
      {
        String choice = args[0];
        switch (choice)
        {
        case "help": 
          p.sendMessage(Config.getDivider());
          p.sendMessage(Config.getMainColor() + "/freeze (name) [-u]" + Config.getBracketColor() + ": " + Config.getOffColor() + "Freeze a player");
          p.sendMessage(Config.getMainColor() + "/gamble (amount)" + Config.getBracketColor() + ": " + Config.getOffColor() + "Gamble for a chance to double your bet");
          p.sendMessage(Config.getMainColor() + "/getstaff" + Config.getBracketColor() + ": " + Config.getOffColor() + "Get staffs attention");
          p.sendMessage(Config.getMainColor() + "/nv" + Config.getBracketColor() + ": " + Config.getOffColor() + "Night vision");
          p.sendMessage(Config.getMainColor() + "/rename (new name)" + Config.getBracketColor() + ": " + Config.getOffColor() + "Rename a Item");
          p.sendMessage(Config.getMainColor() + "/links" + Config.getBracketColor() + ": " + Config.getOffColor() + "Shows the links of the server");
          p.sendMessage(Config.getMainColor() + "/stats [name]" + Config.getBracketColor() + ": " + Config.getOffColor() + "Shows stats of a player");
          p.sendMessage(Config.getMainColor() + "/trash" + Config.getBracketColor() + ": " + Config.getOffColor() + "Shows the links of the server ");
          p.sendMessage(Config.getMainColor() + "/v [name]" + Config.getBracketColor() + ": " + Config.getOffColor() + "Vanish yourself/another player");
          p.sendMessage(Config.getMainColor() + "/monitor" + Config.getBracketColor() + ": " + Config.getOffColor() + "Shows server stats");
          p.sendMessage(Config.getMainColor() + "/sc (message)" + Config.getBracketColor() + ": " + Config.getOffColor() + "Staff chat");
          p.sendMessage(Config.getMainColor() + "/c" + Config.getBracketColor() + ": " + Config.getOffColor() + "Main chat management command");
          p.sendMessage(Config.getMainColor() + "/pot" + Config.getBracketColor() + ": " + Config.getOffColor() + "Stacks potions");
          p.sendMessage(Config.getDivider());
          break;
        case "reload": 
          if (sender.hasPermission("core.reload"))
          {
            this.main.reloadConfig();
            p.sendMessage(Config.getPrefix() + Config.getReloadMessage());
          }
          else
          {
            p.sendMessage(Config.getNoPermMessage());
          }
          break;
        default: 
          p.sendMessage(Config.getPrefix() + Config.getOffColor() + "Invalid args.");
        }
      }
    }
    return false;
  }
}
