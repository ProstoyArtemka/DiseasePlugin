package greenvox.team.ru.database;

import greenvox.team.ru.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapelessRecipe;

public class DatabaseManager {

    public static void applyDiseaseToPlayer(Player p) {
        // Create database and add player into it.
        String name = p.getName();
        DataBase.write(name+".level", 0);
    }

    public static void removeDiseaseFromPlayer(Player p) {
        // Remove player from database.
        String name = p.getName();
        DataBase.delete(name);

    }

    public static boolean isPlayerIsInfected(Player p) {
        // Return true if player in database and false is not.
        String name = p.getName();
        return Main.getData().getConfig().contains(name);
    }

    public static int getDiseaseLevelFromPlayer(Player p) {
        // Return Disease level from database
        if(!isPlayerIsInfected(p))  return -1;

        String name = p.getName();

        return Main.getData().getConfig().getInt(name + ".level");
    }

    public static void setDiseaseLevelToPlayer(Player p, int level) {
        // Set level in database
        String name = p.getName();
        DataBase.write(name+".level", level);
    }
}
