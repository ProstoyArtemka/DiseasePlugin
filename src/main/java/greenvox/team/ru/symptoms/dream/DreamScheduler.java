package greenvox.team.ru.symptoms.dream;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.SchedulerManager;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class DreamScheduler extends BukkitRunnable {
    Player player;


    public DreamScheduler(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        ServerEmoteAPI.setPlayerPlayingEmote(player.getUniqueId(), null);

        Dream.NpcIdList.put(player.getName(), Dream.id);

        Dream.tpInDream(player);
        int timer = new Random().nextInt(30, 60);

        int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), ()->{
            Dream.returnFromDream(player);
            SchedulerManager.cancelTask("Dream.particle."+player.getName());
        }, timer*20);


        Dream.taskIdList.put(player.getName(), taskId);
    }
}
