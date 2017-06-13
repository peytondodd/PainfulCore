package me.hollowdev.core.cmds;

import java.util.Random;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;
import net.milkbowl.vault.economy.Economy;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gambleCmd
  implements CommandExecutor
{
  Main main;
  
  public gambleCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("gamble"))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      Player p = (Player)sender;
      if (args.length != 1)
      {
        p.sendMessage(Utils.color(Config.getInvalidArgs()));
        return true;
      }
      if (NumberUtils.isNumber(args[0]))
      {
        Double bet = Double.valueOf(Double.parseDouble(args[0]));
        if (this.main.getEconomy().has(p, bet.doubleValue()))
        {
          Random rand = new Random();
          if (rand.nextBoolean())
          {
            p.sendMessage(Utils.color(Config.getPrefix() + Config.getGambleWinMsg().replace("%amount%", args[0])));
            this.main.getEconomy().depositPlayer(p, bet.doubleValue());
          }
          else
          {
            p.sendMessage(Utils.color(Config.getPrefix() + Config.getGambleLoseMsg().replace("%amount%", args[0])));
            this.main.getEconomy().withdrawPlayer(p, bet.doubleValue());
          }
        }
        else
        {
          p.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "&7You do not have enough money" + Config.getBracketColor() + "!"));
        }
      }
      else
      {
        p.sendMessage(Utils.color(Config.getPrefix() + "&cYou did not enter a number.."));
      }
    }
    return false;
  }
}
