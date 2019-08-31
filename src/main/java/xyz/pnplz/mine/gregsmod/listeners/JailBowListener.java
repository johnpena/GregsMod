package xyz.pnplz.mine.gregsmod.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class JailBowListener implements Listener {

  private static final String worldName = "Gregbuquerque";
  private static final Double jailCoordX = 265.0;
  private static final Double jailCoordY = 65.0;
  private static final Double jailCoordZ = -31.5;
  private static final Server server = Bukkit.getServer();
  private static final Location jail = new Location(server.getWorld(worldName), jailCoordX, jailCoordY, jailCoordZ);

  @EventHandler
  public void onPlayerInteract(EntityDamageByEntityEvent event) {
    Entity damager = event.getDamager();
    boolean isSpectralArrow = damager.getType().equals(EntityType.SPECTRAL_ARROW);

    if (event.getEntity() instanceof Player && isSpectralArrow) {
      Player player = (Player) event.getEntity();
      Bukkit.getLogger().info("Sending that fucker to jail");
      player.teleport(jail);
    }
  }
}
