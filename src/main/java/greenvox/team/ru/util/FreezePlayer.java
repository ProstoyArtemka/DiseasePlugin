package greenvox.team.ru.util;

import greenvox.team.ru.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FreezePlayer extends BukkitRunnable{
    int progressTimer = 1;
    int time;
    Location location;
    Player player;
    public static void freeze(Player player, int time){
        new FreezePlayer(player, time).runTaskTimer(Main.getInstance(), 0, 1);
    }

    public FreezePlayer(Player player, int time) {
        this.player = player;
        this.location = player.getLocation();
        this.time = time;
    }

    @Override
    public void run() {
        if(progressTimer%100 == 0) cancel();
        player.teleport(location);
        progressTimer++;
    }
}
