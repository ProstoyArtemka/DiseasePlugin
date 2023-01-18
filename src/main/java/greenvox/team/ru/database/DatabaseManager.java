package greenvox.team.ru.database;

import greenvox.team.ru.Main;
import org.bukkit.entity.Player;

public class DatabaseManager {

    public static void applyDiseaseToPlayer(Player p) {
        DataBase.write(p.getName() + ".level", 0);
    }

    public static void removeDiseaseFromPlayer(Player p) {
        DataBase.delete(p.getName());
    }

    public static boolean isPlayerIsInfected(Player p) {
        return Main.getData().getConfig().contains(p.getName());
    }

    public static int getDiseaseLevelFromPlayer(Player p) {
        if (!isPlayerIsInfected(p)) return -1;
        String name = p.getName();

        return Main.getData().getConfig().getInt(name + ".level");
    }

    public static void setDiseaseLevelToPlayer(Player p, int level) {
        DataBase.write(p.getName() + ".level", level);
    }
}