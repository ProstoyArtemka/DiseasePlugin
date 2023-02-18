package greenvox.team.ru.events;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.util.FreezePlayerScheduler;
import greenvox.team.ru.util.SchedulerManager;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;


public class OnVaccineEatEvent implements Listener {

    @EventHandler
    public void onVaccineEat(PlayerItemConsumeEvent e) {
        if (DatabaseManager.getDiseaseLevelFromPlayer(e.getPlayer()) == -1) return;
        if (DatabaseManager.isAteVaccine(e.getPlayer())) return;
        if (!DatabaseManager.isPlayerIsInfected(e.getPlayer())) return;

        String message = "&cВы судорожно падаете на пол.";
        Player player = e.getPlayer();

        player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20 * 20,1));
        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', message));
        player.setHealth(20);

        FreezePlayerScheduler.freeze(player, 400);
        ServerEmoteAPI.forcePlayEmote(player.getUniqueId(), ServerEmoteAPI.getEmote(UUID.fromString("465aacc5-0000-0000-0000-000000000000")));

        DatabaseManager.setAteVaccine(player);

        SchedulerManager.runTaskTimer("Vaccine_run" + player.getName(), new VaccineRunnable(player), 5 * 20, 5 * 20);
        SchedulerManager.runTaskTimer("HeartBeat_run" + player.getName(), new HeartBeatRunnable(player, 25),1, 20);
    }
}

    class HeartBeatRunnable extends BukkitRunnable {

        private final Player player;
        private int Count = 0;
        private final int Time;

        public HeartBeatRunnable(Player player, int time) {
            this.player = player;
            this.Time = time;
        }

        @Override
        public void run() {
            if (Count == Time) cancel();

            player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_HEARTBEAT, 10 ,1);
            Count++;
        }
    }

