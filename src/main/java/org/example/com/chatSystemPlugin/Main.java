package org.example.com.chatSystemPlugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {
    
    private HashMap<UUID, UUID> recentMessage;
    
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
        
        recentMessage = new HashMap<>();
    }
    
    public HashMap<UUID, UUID> getRecentMessage() {
        return recentMessage;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        
        recentMessage.remove(event.getPlayer().getUniqueId());
        
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    
}
