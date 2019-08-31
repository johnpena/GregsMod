package xyz.pnplz.mine.gregsmod.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LightningTridentListener implements Listener {

  private final static Server server = Bukkit.getServer();

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    boolean isTrident = player.getInventory().getItemInMainHand().getType().equals(Material.TRIDENT);
    boolean isLeftClick = event.getAction() == Action.LEFT_CLICK_BLOCK;

    if (isTrident && isLeftClick) {
      Bukkit.getLogger().info("ISSUING LIGHTNING STRIKE!!!111");
      issueStrike(player, event.getClickedBlock().getLocation());
    }
  }

  private void issueStrike(Player fromPlayer, Location strikeLocation) {
    server.getScheduler().scheduleSyncDelayedTask(
        Bukkit.getServer().getPluginManager().getPlugin("GregsMod"),
        () -> fromPlayer.getWorld().strikeLightning(strikeLocation)
    );
  }
}
