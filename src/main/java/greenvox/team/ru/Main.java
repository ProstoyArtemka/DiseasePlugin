package greenvox.team.ru;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main Instance;

    @Override
    public void onEnable() {
        Instance = this;


    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return Instance;
    }
}
