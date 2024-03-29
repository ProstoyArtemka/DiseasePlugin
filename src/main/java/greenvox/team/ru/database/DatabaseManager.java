package greenvox.team.ru.database;

import greenvox.team.ru.Main;
import org.bukkit.entity.Player;

public class DatabaseManager {

    public static void applyDiseaseToPlayer(Player p) {
        DataBase.write(p.getName() + ".level", 0);
    }
    public static void setDiseaseLevelToPlayer(Player p, int level) {
        DataBase.write(p.getName() + ".level", level);
    }
    public static void applyBloodLevelToPlayer(Player p) {
        DataBase.write(p.getName() + ".bloodLessLevel", 1);
    }

    public static void setBloodLevelToPlayer(Player p, int level) {
        DataBase.write(p.getName() + ".bloodLessLevel", level);
    }

    public static boolean isPlayerHaveBloodLevel(Player p) {
        return !Main.getData().getConfig().contains(p.getName() + ".bloodLessLevel");
    }

    public static void setAteVaccine(Player player) {
        setDiseaseLevelToPlayer(player, -2);
    }

    public static int getDiseaseLevelFromPlayer(Player p) {
        if (!isPlayerIsInfected(p)) return -1;
        String name = p.getName();

        return Main.getData().getConfig().getInt(name + ".level");
    }
    public static boolean isAteVaccine(Player player) {
        if (Main.getData().getConfig().getInt(player.getName() + ".level") == -2) return true;
        return false;
    }

    public static boolean isAtePills(Player player) {
        if (Main.getData().getConfig().getInt(player.getName() + ".level") == -1) return true;
        return false;
    }

    public static void removeDiseaseFromPlayer(Player p) {
        DataBase.delete(p.getName());
    }

    public static void removeBloodLevelFromPlayer(Player p) {
        DataBase.delete(p.getName() + ".bloodLessLevel");
    }

    public static boolean isPlayerIsInfected(Player p) {
        if (isAtePills(p)) return false;
        if (isAteVaccine(p)) return false;
        return Main.getData().getConfig().contains(p.getName());
    }


    public static int getBloodLevelFromPlayer(Player p) {
        if (isPlayerHaveBloodLevel(p)) return -1;

        return Main.getData().getConfig().getInt(p.getName() + ".bloodLessLevel");
    }




}