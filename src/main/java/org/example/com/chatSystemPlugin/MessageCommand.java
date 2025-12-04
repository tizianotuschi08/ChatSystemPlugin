package org.example.com.chatSystemPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {
    
    private Main main;
    public MessageCommand(Main main) {
        this.main = main;
    }
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        
        Player  player = (Player) commandSender;
        
        if(strings.length >= 2)
        {
            if (Bukkit.getPlayerExact(strings[0]) != null)
            {
                Player target = Bukkit.getPlayerExact(strings[0]);
                StringBuilder builder = new StringBuilder();
                
                for (int i = 1; i < strings.length; i++)
                {
                    builder.append(strings[i]).append(" ");
                }
                
                player.sendMessage(ChatColor.GRAY + "You -> " + target.getName() + ": " + builder.toString());
                target.sendMessage(ChatColor.GREEN + player.getName() + " -> " + builder.toString());
                
                main.getRecentMessage().put(player.getUniqueId(), target.getUniqueId());
            }
            else
            {
                player.sendMessage(ChatColor.RED + "Player not found!");
            }
        
        }
        else
        {
            player.sendMessage(ChatColor.RED + "Invalid arguments! Use /message <player> <message>.");
        }
        
        return false;
    }
}
