package greenvox.team.ru.util;

import greenvox.team.ru.database.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BloodLevelUtil {

    public static void addBloodLevel(Player player) {

        if (DatabaseManager.isPlayerHaveBloodLevel(player))
            DatabaseManager.applyBloodLevelToPlayer(player);
        else
            DatabaseManager.setBloodLevelToPlayer(player, DatabaseManager.getBloodLevelFromPlayer(player) + 1);
    }

    public static void bloodLevelMessages(Player player) {
        if (DatabaseManager.getBloodLevelFromPlayer(player) == 2) {

            String str = "&cВаше тело становится слабее";
            player.sendActionBar(ChatColor.translateAlternateColorCodes('&', str));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10 * 20,1));
        }

        if (DatabaseManager.getBloodLevelFromPlayer(player) == 4) {

            String str = "&cВаши конечности начинают неметь";
            player.sendActionBar(ChatColor.translateAlternateColorCodes('&', str));
        }
    }

    public static void bloodLevelDeath(Player player) {
        if (DatabaseManager.getBloodLevelFromPlayer(player) > 5) {
            DatabaseManager.removeBloodLevelFromPlayer(player);
            player.setHealth(0);
        }
    }
}
