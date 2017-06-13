package me.hollowdev.core.monitor;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;
import me.hollowdev.core.events.lagListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class monitorCmd
  implements CommandExecutor
{
  Main main;
  Runtime runtime = Runtime.getRuntime();
  
  public monitorCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    double tps = Math.round(lagListener.getTPS() * 100.0D) / 100.0D;
    double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
    String uptime = this.main.getUptime();
    if ((cmd.getName().equalsIgnoreCase("monitor")) && (sender.hasPermission("core.monitor.use")) && (args.length < 1))
    {
      sender.sendMessage(Config.getDivider());
      sender.sendMessage(Utils.color(Config.getBracketColor() + "[" + Config.getMainColor() + "Monitor" + Config.getBracketColor() + "} " + Config.getOffColor() + Config.getServerName()));
      sender.sendMessage(Utils.color(""));
      sender.sendMessage(Utils.color(Config.getBracketColor() + ">" + Config.getOffColor() + " Uptime" + Config.getBracketColor() + ": " + uptime));
      sender.sendMessage(Utils.color(Config.getBracketColor() + ">" + Config.getOffColor() + " Allocated Memory" + Config.getBracketColor() + ": " + Config.getMainColor() + this.runtime.totalMemory() / 1048576L + " MB"));
      sender.sendMessage(Utils.color(Config.getBracketColor() + ">" + Config.getOffColor() + " Used Memory" + Config.getBracketColor() + ": " + Config.getMainColor() + (this.runtime.totalMemory() - this.runtime.freeMemory()) / 1048576L + " MB"));
      sender.sendMessage(Utils.color(Config.getBracketColor() + ">" + Config.getOffColor() + " Free Memory" + Config.getBracketColor() + ": " + Config.getMainColor() + this.runtime.freeMemory() / 1048576L + " MB"));
      sender.sendMessage(Utils.color(""));
      sender.sendMessage(Utils.color(Config.getBracketColor() + ">" + Config.getOffColor() + " TPS" + Config.getBracketColor() + ": " + Config.getMainColor() + tps));
      sender.sendMessage(Utils.color(Config.getBracketColor() + "?" + Config.getOffColor() + " Lag Percentage" + Config.getBracketColor() + ": " + Config.getMainColor() + lag * 5.0D + "%"));
      sender.sendMessage(Config.getDivider());
    }
    return false;
  }
}
