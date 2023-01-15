package greenvox.team.ru;

import greenvox.team.ru.database.DataBase;
import greenvox.team.ru.managers.DiseaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main Instance;
    private static DataBase Data;

    @Override
    public void onEnable() {
        Instance = this;
        saveDefaultConfig();

        Data = new DataBase("database.yml");

        DiseaseManager.initSymptoms();
        DiseaseManager.runDiseaseTimer();
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return Instance;
    }
    public static DataBase getData(){
        return Data;
    }
}
