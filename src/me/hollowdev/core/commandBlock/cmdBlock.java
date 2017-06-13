package me.hollowdev.core.commandBlock;

import java.util.HashMap;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class cmdBlock
  implements Listener
{
  Main main;
  
  public cmdBlock(Main main)
  {
    this.main = main;
  }
  
  @EventHandler
  public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
  {
    Player p = e.getPlayer();
    String command = e.getMessage().substring(1).split(" ")[0].toLowerCase();
    HashMap<String, String> commands = Config.getCommands();
    if ((!p.hasPermission("core.cmdblock.bypass")) && (commands.containsKey(command)))
    {
      e.setCancelled(true);
      p.sendMessage(Utils.color((String)commands.get(command)));
    }
  }
}
