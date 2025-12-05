package org.example.com.chatSystemPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {
    
    private Main main;
    private boolean AllowReply;
    public ReplyCommand(Main main) {
        this.main = main;
        AllowReply = main.getConfig().getBoolean("AllowReply");
    }
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) return true;
            Player player = (Player) commandSender;

            if (strings.length >= 1) {
                if (main.getRecentMessage().containsKey(player.getUniqueId())) {

                    UUID id = main.getRecentMessage().get(player.getUniqueId());

                    if (Bukkit.getPlayer(id) != null) {

                        Player target = Bukkit.getPlayer(id);
                        StringBuilder builder = new StringBuilder();

                        for (int i = 0; i < strings.length; i++) {
                            builder.append(strings[i]).append(" ");
                        }

                        player.sendMessage(ChatColor.GRAY + "You -> " + target.getName() + ": " + builder.toString());
                        target.sendMessage(ChatColor.GREEN + player.getName() + " -> " + builder.toString());

                        if (AllowReply) {
                            main.getRecentMessage().put(target.getUniqueId(), player.getUniqueId());
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "Player gone offline!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have a recent message!");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid arguments! Use /reply <message>.");
            }

        return true;
    }
}
