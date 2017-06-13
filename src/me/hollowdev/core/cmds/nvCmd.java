package me.hollowdev.core.cmds;

import java.util.List;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class nvCmd
  implements CommandExecutor
{
  Main main;
  
  public nvCmd(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("nv")) && (sender.hasPermission("core.nv")))
    {
      if (!(sender instanceof Player))
      {
        Utils.noPlayer();
        return true;
      }
      Player player = (Player)sender;
      if (Main.getInstance().NV.contains(player.getName()))
      {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        player.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "&cNight Vision Deactivated!"));
        Main.getInstance().NV.remove(player.getName());
        return true;
      }
      player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 2));
      
      player.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "&aNight Vision Activated!"));
      Main.getInstance().NV.add(player.getName());
    }
    return false;
  }
}
