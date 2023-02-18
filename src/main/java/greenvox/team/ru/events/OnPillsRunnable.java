package greenvox.team.ru.events;

import greenvox.team.ru.database.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class OnPillsRunnable extends BukkitRunnable {

    private final Player player;
    private static HashMap<String, Integer> diseaseLvl;

    public OnPillsRunnable(Player player, HashMap<String, Integer> diseaseLvl) {
        this.player = player;
        OnPillsRunnable.diseaseLvl = diseaseLvl;
    }

    @Override
    public void run() {
        String message = "&cВам снова становится плохо";

        DatabaseManager.setDiseaseLevelToPlayer(player, diseaseLvl.get(player.getName()));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1));

        diseaseLvl.remove(player.getName());

        OnPillsEatEvent.players.remove(player.getName());
        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', message));

    }

    public static void onDisable() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (DatabaseManager.getDiseaseLevelFromPlayer(player) >= 0) return;
            if (DatabaseManager.isAteVaccine(player)) return;
            if (diseaseLvl.get(player.getName()) == null) return;

            if (DatabaseManager.getDiseaseLevelFromPlayer(player) < 0) {
                DatabaseManager.setDiseaseLevelToPlayer(player, diseaseLvl.get(player.getName()));
            }
        }
    }
}

