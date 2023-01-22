package greenvox.team.ru;

import greenvox.team.ru.commands.DiseaseCommand;
import greenvox.team.ru.database.DataBase;
import greenvox.team.ru.disease.DiseaseManager;
import greenvox.team.ru.recipes.MaskRecipe;
import org.bukkit.Bukkit;
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

        DiseaseCommand.init();

        registerCrafts();
    }

    private void registerCrafts() {
        if (Bukkit.getRecipe(MaskRecipe.MaskKey) == null) Bukkit.addRecipe(new MaskRecipe());
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
