package brawl.snaxv2.lobby;

import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;


public class main extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
    	Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }
    @Override
    public void onDisable() {}
    
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
	}
	
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
		
		if (event.getPlayer().getLocation().add(0, -0.5, 0).getBlock().getType().equals(Material.REDSTONE_BLOCK)) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
		    out.writeUTF("warz19");
		    event.getPlayer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
		}
	
	}
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		permissionStuff perm = new permissionStuff();
		Bukkit.broadcastMessage("[" + perm.getRankColor(event.getPlayer()) + perm.getRankName(event.getPlayer()) + ChatColor.WHITE + "] " + event.getPlayer().getName() + " > " +  event.getMessage());
    }
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		permissionStuff perm = new permissionStuff();
		if (!(perm.getRankId(event.getPlayer())>4)) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		permissionStuff perm = new permissionStuff();
		if (!(perm.getRankId(event.getPlayer())>4)) {
			event.setCancelled(true);
		}
	}
	
}
