package me.hollowdev.core.chatManagement;

import me.hollowdev.core.Config;
import me.hollowdev.core.Main;
import me.hollowdev.core.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Chat
  implements CommandExecutor
{
  Main main;
  
  public Chat(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("c"))
    {
      if (args.length == 0)
      {
        showHelp(sender);
        return true;
      }
      if (args.length == 1)
      {
        if (args[0].equalsIgnoreCase("lock"))
        {
          if (sender.hasPermission("c.lock"))
          {
            if (!this.main.isChatState())
            {
              this.main.setChatState(true);
              this.main.setDisabler(sender.getName());
              Bukkit.broadcastMessage(Utils.color(Config.getDivider()));
              Bukkit.broadcastMessage(Utils.color(Config.getChatLocked().replace("$locker", this.main.getDisabler())));
              Bukkit.broadcastMessage(Utils.color(Config.getDivider()));
              return true;
            }
            sender.sendMessage(Utils.color(Config.getPrefix() + Config.getOffColor() + "The chat is already locked" + Config.getBracketColor() + "!"));
            return true;
          }
          sender.sendMessage(Utils.color(Config.getPrefix() + Config.getNoPermMessage()));
          return true;
        }
        if (args[0].equalsIgnoreCase("unlock"))
        {
          if (sender.hasPermission("c.lock"))
          {
            if (this.main.isChatState())
            {
              this.main.setChatState(false);
              this.main.setDisabler(sender.getName());
              Bukkit.broadcastMessage(Utils.color(Config.getDivider()));
              Bukkit.broadcastMessage(Utils.color(Config.getChatUnlocked().replace("$locker", this.main.getDisabler())));
              Bukkit.broadcastMessage(Utils.color(Config.getDivider()));
            }
            else
            {
              sender.sendMessage(Config.getPrefix() + Config.getOffColor() + "The chat is already unlocked" + Config.getBracketColor() + "!");
            }
          }
          else {
            sender.sendMessage(Config.getPrefix() + Config.getNoPermMessage());
          }
        }
        else if ((args[0].equalsIgnoreCase("clear")) && 
          (sender.hasPermission("c.clear"))) {
          clear(sender.getName());
        }
      }
    }
    return false;
  }
  
  private void showHelp(CommandSender sender)
  {
    sender.sendMessage(Utils.color(Config.getDivider()));
    sender.sendMessage("");
    sender.sendMessage(Utils.color(Config.getBracketColor() + "[" + Config.getMainColor() + "Chat Management" + Config.getBracketColor() + "]"));
    if (sender.hasPermission("c.lock"))
    {
      sender.sendMessage(Utils.color(Config.getBracketColor() + " *" + Config.getMainColor() + " /c lock" + Config.getBracketColor() + ":" + 
        Config.getOffColor() + " Locks the chat."));
      sender.sendMessage(Utils.color(Config.getBracketColor() + " *" + Config.getMainColor() + " /c unlock" + Config.getBracketColor() + ": " + 
        Config.getOffColor() + " Unlocks the chat."));
    }
    if (sender.hasPermission("c.clear")) {
      sender.sendMessage(Utils.color(Config.getBracketColor() + " *" + Config.getMainColor() + " /c clear" + Config.getBracketColor() + ": " + 
        Config.getOffColor() + " Clears the chat"));
    }
    sender.sendMessage("");
    sender.sendMessage(Utils.color(Config.getDivider()));
  }
  
  private void clear(String name)
  {
    for (int i = 0; i < 101; i++) {
      Bukkit.broadcastMessage("");
    }
    Bukkit.broadcastMessage(Config.getDivider());
    Bukkit.broadcastMessage(
      Utils.color(Config.getChatCleared().replace("$locker", name)));
    Bukkit.broadcastMessage(Config.getDivider());
  }
}
