package greenvox.team.ru;

import greenvox.team.ru.managers.DiseaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main Instance;

    @Override
    public void onEnable() {
        Instance = this;

        DiseaseManager.runDiseaseTimer();
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return Instance;
    }
}
