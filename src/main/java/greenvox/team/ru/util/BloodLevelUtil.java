package greenvox.team.ru.util;

import greenvox.team.ru.database.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BloodLevelUtil {

    private static final List<String> bloodMessages = Arrays.asList(
            "&cВаше тело становится слабее",
            "&cВаши конечности начинают неметь",
            "&cВаш слух становится слабее",
            "&cВаши конечности холоднеют",
            "&cВ ваших глаха начинает темнеть"
    );
   private static final Random randomFromList = new Random();
   private static final int message = randomFromList.nextInt(bloodMessages.size());
   private static final String randomElement = bloodMessages.get(message);

    public static void addBloodLevel(Player player) {

        if (DatabaseManager.isPlayerHaveBloodLevel(player))
            DatabaseManager.applyBloodLevelToPlayer(player);
        else
            DatabaseManager.setBloodLevelToPlayer(player, DatabaseManager.getBloodLevelFromPlayer(player) + 1);
    }

    public static void bloodLevelMessages(Player player) {
        if (DatabaseManager.getBloodLevelFromPlayer(player) == 5) {



            player.sendActionBar(ChatColor.translateAlternateColorCodes('&', randomElement));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10 * 20,1));
        }
    }

    public static void bloodLevelDeath(Player player) {
        if (DatabaseManager.getBloodLevelFromPlayer(player) > 5) {
            DatabaseManager.removeBloodLevelFromPlayer(player);
            player.setHealth(0);
        }
    }
}
