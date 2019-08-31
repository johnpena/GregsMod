package xyz.pnplz.mine.gregsmod.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.pnplz.mine.gregsmod.GregsModJavaPlugin;

public class WhereCommandExecutor implements CommandExecutor {
  private final GregsModJavaPlugin plugin;

  public WhereCommandExecutor(GregsModJavaPlugin plugin){
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (args.length > 0) {
      for (String arg : args) {
        Player player = Bukkit.getServer().getPlayer(arg);
        Location location = player.getLocation();
        sender.sendMessage(getDisplayMessage(player, location));
      }

    } else {
      for (Player player : Bukkit.getServer().getOnlinePlayers()) {
        Location location = player.getLocation();
        sender.sendMessage(getDisplayMessage(player, location));
      }
    }

    return true;
  }

  private String getDisplayMessage(Player player, Location location) {
    return String.format("%s is at location %s %s %s",
        player.getDisplayName(),
        location.getBlockX(),
        location.getBlockY(),
        location.getBlockZ());
  }
}
