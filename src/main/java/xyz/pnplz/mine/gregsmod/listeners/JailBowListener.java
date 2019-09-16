package xyz.pnplz.mine.gregsmod.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class JailBowListener implements Listener {

  private static final String WORLD_NAME_ENV_VAR = "WORLD_NAME";

  private static final String defaultWorldName = "Gregbuquerque";
  private static final Double defaultJailCoordX = 265.0;
  private static final Double defaultJailCoordY = 65.0;
  private static final Double defaultJailCoordZ = -31.5;

  private static final Server server = Bukkit.getServer();

  @EventHandler
  public void onPlayerInteract(EntityDamageByEntityEvent event) {
    Entity damager = event.getDamager();
    boolean isSpectralArrow = damager.getType().equals(EntityType.SPECTRAL_ARROW);

    if (event.getEntity() instanceof Player && isSpectralArrow) {
      Player player = (Player) event.getEntity();
      Bukkit.getLogger().info("Sending that fucker to jail");
      player.teleport(getJailLocation());
    }
  }

  private String getWorldName() {
    String name = System.getenv(WORLD_NAME_ENV_VAR);
    if (name.equals("")) {
      return defaultWorldName;
    }
    return name;
  }

  private Double getCoord(String envVarName, Double defaultVal) {
    String coordString = System.getenv(envVarName);
    if (coordString.equals("")) {
      return defaultVal;
    }

    try {
      Double coord = Double.parseDouble(coordString);
      return coord;
    } catch (NumberFormatException e) {
      return defaultVal;
    }
  }

  private Double getJailCoordinateX() {
    return getCoord("JAIL_COORD_X", defaultJailCoordX);
  }

  private Double getJailCoordinateY() {
    return getCoord("JAIL_COORD_Y", defaultJailCoordY);
  }

  private Double getJailCoordinateZ() {
    return getCoord("JAIL_COORD_Z", defaultJailCoordZ);
  }
  private Location getJailLocation() {
    return new Location(
        server.getWorld(getWorldName()),
        getJailCoordinateX(),
        getJailCoordinateY(),
        getJailCoordinateZ()
    );
  }
}
