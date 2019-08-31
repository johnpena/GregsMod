package xyz.pnplz.mine.gregsmod;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.pnplz.mine.gregsmod.commands.WhereCommandExecutor;
import xyz.pnplz.mine.gregsmod.listeners.JailBowListener;
import xyz.pnplz.mine.gregsmod.listeners.LightningTridentListener;

public class GregsModJavaPlugin extends JavaPlugin {
  @Override
  public void onEnable() {
    getLogger().info("onEnable has been invoked!");

    // Enable commands
    WhereCommandExecutor whereCommand = new WhereCommandExecutor(this);
    getCommand("where").setExecutor(whereCommand);

    // Enable listeners
    getServer().getPluginManager().registerEvents(new LightningTridentListener(), this);
    getServer().getPluginManager().registerEvents(new JailBowListener(), this);
  }

  @Override
  public void onDisable() {
    getLogger().info("onDisable has been invoked!");
  }
}
