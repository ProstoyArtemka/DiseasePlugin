package greenvox.team.ru.events;

import greenvox.team.ru.database.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPillsRunnable extends BukkitRunnable {

    private static Player player;
    public static int diseaseLvl;

    public OnPillsRunnable(Player player, int diseaseLvl) {
        this.player = player;
        this.diseaseLvl = diseaseLvl;
    }

    @Override
    public void run() {
        String message = "&cВам снова становится плохо";

        DatabaseManager.setDiseaseLevelToPlayer(player, diseaseLvl);
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1));

        OnPillsEatEvent.players.remove(player.getName());
        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', message));

    }

    public static void onDisable() {
        if (player == null) return;
            DatabaseManager.setDiseaseLevelToPlayer(player, diseaseLvl);
        }
    }

