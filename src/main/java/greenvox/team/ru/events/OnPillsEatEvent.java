package greenvox.team.ru.events;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.recipes.PillsCraft;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.ArrayList;
import java.util.List;

public class OnPillsEatEvent implements Listener {

    public static final List<String> players = new ArrayList<>();

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        if (!DatabaseManager.isPlayerIsInfected(player)) return;
        if (players.contains(player.getName())) return;

        int lvl = DatabaseManager.getDiseaseLevelFromPlayer(player);

        String message = "&aВаша болезнь отступает";

        if (e.getItem().getItemMeta().getPersistentDataContainer().has(PillsCraft.PillsTag)) {
            DatabaseManager.setDiseaseLevelToPlayer(player, -1);

            players.add(player.getName());

            player.sendActionBar(ChatColor.translateAlternateColorCodes('&', message));

        }
        SchedulerManager.runTaskLater("Pills_run" + player.getName(), new OnPillsRunnable(player, lvl), 10 * 300);
    }


}



