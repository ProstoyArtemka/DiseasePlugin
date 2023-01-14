package greenvox.team.ru;

import greenvox.team.ru.database.DataBase;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main Instance;
    private DataBase data;

    @Override
    public void onEnable() {
        Instance = this;
        saveDefaultConfig();

        data = new DataBase("database.yml");


    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return Instance;
    }

    public static DataBase getData(){
        return Instance.data;
    }
}
