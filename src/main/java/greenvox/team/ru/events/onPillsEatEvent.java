package greenvox.team.ru.events;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.recipes.PillsRecipe;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class onPillsEatEvent implements Listener {
    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();

        if (e.getItem().getItemMeta().getPersistentDataContainer().has(PillsRecipe.PillsTag)) {

            DatabaseManager.setDiseaseLevelToPlayer(player, DatabaseManager.getDiseaseLevelFromPlayer(player) - 1);

            if (DatabaseManager.getDiseaseLevelFromPlayer(player) > 0) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 3));
            }
            if (DatabaseManager.getDiseaseLevelFromPlayer(player) <= 0) {
                DatabaseManager.setDiseaseLevelToPlayer(player, 0);
            }
        }
    }
}
