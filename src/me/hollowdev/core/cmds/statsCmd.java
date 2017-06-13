package me.hollowdev.core.cmds;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.UUID;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class statsCmd
  implements CommandExecutor
{
  Main main;
  
  public statsCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("pstats"))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      Player p = (Player)sender;
      if (args.length == 0)
      {
        File playerFile = getPlayerFile(p.getUniqueId().toString());
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        dividers(sender);
        p.sendMessage(Utils.color(Config.getBracketColor() + "[" + Config.getMainColor() + "Stats" + Config.getBracketColor() + "] " + Config.getOffColor() + p.getName()));
        p.sendMessage(Utils.color(Config.getOffColor() + "Logins" + Config.getBracketColor() + ": " + Config.getMainColor() + playerData.getInt("stats.logins")));
        p.sendMessage(Utils.color(Config.getOffColor() + "Kills" + Config.getBracketColor() + ": " + Config.getMainColor() + playerData.getInt("stats.kills")));
        p.sendMessage(Utils.color(Config.getOffColor() + "Deaths: " + Config.getMainColor() + playerData.getInt("stats.deaths")));
        p.sendMessage(Utils.color(Config.getOffColor() + "UUID" + Config.getBracketColor() + ": " + Config.getMainColor() + p.getUniqueId().toString()));
        p.sendMessage(Utils.color(Config.getOffColor() + "Gamemode" + Config.getOffColor() + ": " + Config.getMainColor() + p.getGameMode()));
        if (p.hasPermission("core.stats.admin")) {
          p.sendMessage(Utils.color(Config.getOffColor() + "Last IP" + Config.getBracketColor() + ": " + Config.getMainColor() + p.getAddress().getAddress()));
        }
        dividers(sender);
      }
      if (args.length == 1)
      {
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null)
        {
          File playerFile = getPlayerFile(target.getUniqueId().toString());
          FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
          dividers(sender);
          p.sendMessage(Utils.color(Config.getBracketColor() + "[" + Config.getMainColor() + "Stats" + Config.getBracketColor() + "] " + Config.getOffColor() + p.getName()));
          p.sendMessage(Utils.color(Config.getOffColor() + "Logins" + Config.getBracketColor() + ": " + Config.getMainColor() + playerData.getInt("stats.logins")));
          p.sendMessage(Utils.color(Config.getOffColor() + "Kills" + Config.getBracketColor() + ": " + Config.getMainColor() + playerData.getInt("stats.kills")));
          p.sendMessage(Utils.color(Config.getOffColor() + "Deaths: " + Config.getMainColor() + playerData.getInt("stats.deaths")));
          p.sendMessage(Utils.color(Config.getOffColor() + "UUID" + Config.getBracketColor() + ": " + Config.getMainColor() + p.getUniqueId().toString()));
          p.sendMessage(Utils.color(Config.getOffColor() + "Gamemode" + Config.getOffColor() + ": " + Config.getMainColor() + p.getGameMode()));
          if (p.hasPermission("core.stats.admin")) {
            p.sendMessage(Utils.color(Config.getOffColor() + "Last IP" + Config.getBracketColor() + ": " + Config.getMainColor() + p.getAddress().getAddress()));
          }
          dividers(sender);
        }
        else
        {
          p.sendMessage(Utils.color(Config.getPrefix() + " &7Can't find that player"));
        }
      }
    }
    return false;
  }
  
  private void dividers(CommandSender sender)
  {
    sender.sendMessage(Utils.color("&8&m-----------------------------------------"));
  }
  
  private File getPlayerFile(String uuid)
  {
    File playerFile = new File(Main.getInstance().getDataFolder() + File.separator + "stats", uuid + ".yml");
    return playerFile;
  }
}
