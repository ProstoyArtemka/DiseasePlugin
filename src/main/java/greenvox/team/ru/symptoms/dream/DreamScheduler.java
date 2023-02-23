package greenvox.team.ru.symptoms.dream;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.SchedulerManager;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class DreamScheduler extends BukkitRunnable {
    Player player;


    public DreamScheduler(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        ServerEmoteAPI.setPlayerPlayingEmote(player.getUniqueId(), null);


        Dream.tpInDream(player);
        int timer = new Random().nextInt(60, 120);

        int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), ()->{
            Dream.returnFromDream(player);
            SchedulerManager.cancelTask("Dream.particle."+player.getName());
        }, timer* 20L);


        Dream.returnTaskIdList.put(player.getName(), taskId);
    }
}
