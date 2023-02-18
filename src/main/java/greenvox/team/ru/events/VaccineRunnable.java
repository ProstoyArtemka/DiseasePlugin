package greenvox.team.ru.events;

import greenvox.team.ru.util.SchedulerManager;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class VaccineRunnable extends BukkitRunnable {

    private final Player player;
    private int Count = 0;

    private static final List<String> messages = Arrays.asList(
            "&cВаши конечности сводит от напряжения в мышцах.",
            "&cВаше сердце вот-вот выпрыгнет из груди.",
            "&cНеужели что-то пошло не так?",
            "&aВаша болезнь излечена."
    );
    Iterator<String> stringIterator = messages.iterator();


    public VaccineRunnable(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (!stringIterator.hasNext()) return;

        player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().subtract(0,1,0), 10, new Particle.DustOptions(Color.GREEN, 2));
        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', stringIterator.next()));
        player.damage(3);

        Count++;

        if (Count >= 4) {
            ServerEmoteAPI.setPlayerPlayingEmote(player.getUniqueId(), null);
            SchedulerManager.cancelTask("Vaccine_run" + player.getName());
        }
    }
}