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
import java.util.HashMap;
import java.util.List;

public class OnPillsEatEvent implements Listener {

    public static final List<String> players = new ArrayList<>();
    public HashMap<String, Integer> playersLvl = new HashMap<>();

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        if (!DatabaseManager.isPlayerIsInfected(player)) return;
        if (players.contains(player.getName())) return;

        String message = "&aВаша болезнь отступает";

        if (e.getItem().getItemMeta().getPersistentDataContainer().has(PillsCraft.PillsTag)) {
            players.add(player.getName());
            playersLvl.put(player.getName(), DatabaseManager.getDiseaseLevelFromPlayer(player));

            DatabaseManager.setDiseaseLevelToPlayer(player, -1);
            player.sendActionBar(ChatColor.translateAlternateColorCodes('&', message));

            SchedulerManager.runTaskLater("Pills_run" + player.getName(), new OnPillsRunnable(player, playersLvl), 10 * 20);
        }

    }


}



