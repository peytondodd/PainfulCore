package me.hollowdev.core.cmds;

import java.util.HashMap;
import java.util.UUID;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getStaffCmd
  implements CommandExecutor
{
  Main main;
  private HashMap<UUID, Long> cooldown = new HashMap();
  
  public getStaffCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("getstaff"))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      Player p = (Player)sender;
      long lastUsed = 0L;
      if (this.cooldown.containsKey(p.getUniqueId())) {
        lastUsed = ((Long)this.cooldown.get(p.getUniqueId())).longValue();
      }
      int coolmillis = 6000;
      if (System.currentTimeMillis() - lastUsed >= coolmillis)
      {
        for (Player player : Bukkit.getOnlinePlayers()) {
          if (player.hasPermission("core.getstaff.read"))
          {
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
            player.sendMessage(Utils.color(Config.getMainColor() + "&4 ALERT" + Config.getOffColor() + " Player " + Config.getMainColor() + p.getName() + Config.getOffColor() + " requires staff assistance!"));
          }
        }
        p.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "A staff member has been requested."));
        this.cooldown.put(p.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
        return true;
      }
      int timeRemaining = (int)(60L - (System.currentTimeMillis() - lastUsed) / 1000L);
      sender.sendMessage(Utils.color(Config.getPrefix() + "You must wait 1 min. before using this again."));
    }
    return false;
  }
}
