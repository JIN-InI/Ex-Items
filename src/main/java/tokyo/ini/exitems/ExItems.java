package tokyo.ini.exitems;

import org.bukkit.plugin.java.JavaPlugin;

public final class ExItems extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Starting");
        getCommand("exitems").setExecutor(new CommandClass());
        getServer().getPluginManager().registerEvents(new EventClass(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you");
    }
}
