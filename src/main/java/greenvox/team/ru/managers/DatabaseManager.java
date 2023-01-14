package greenvox.team.ru.managers;

import org.bukkit.entity.Player;

public class DatabaseManager {

    public static void applyDiseaseToPlayer(Player p) {
        // Create database and add player into it.
    }

    public static void removeDiseaseFromPlayer(Player p) {
        // Remove player from database.
    }

    public static boolean isPlayerIsInfected(Player p) {
        // Return true if player in database and false is not.
        return false;
    }

    public static int getDiseaseLevelFromPlayer(Player p) {
        // Return Disease level from database
        return -1;
    }

    public static void setDiseaseLevelToPlayer(Player p, int level) {
        // Set level in database
    }
}
