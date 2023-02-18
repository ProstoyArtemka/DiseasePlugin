package greenvox.team.ru.util;

import greenvox.team.ru.Main;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FreezePlayerScheduler extends BukkitRunnable{
    int time;
    int Count = 0;
    Location location;
    Player player;
    public static void freeze(Player player, int time){
        new FreezePlayerScheduler(player, time).runTaskTimer(Main.getInstance(), 0, 1);
    }

    public FreezePlayerScheduler(Player player, int time) {
        this.player = player;
        this.location = player.getLocation();
        this.time = time;
    }

    @Override
    public void run() {
        if (Count == time) cancel();
        player.teleport(location);
        Count++;
    }
}
