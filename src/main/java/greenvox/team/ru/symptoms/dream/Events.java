package greenvox.team.ru.symptoms.dream;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.SchedulerManager;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Events implements Listener {

    public static ArrayList<String> quitedPlayers = new ArrayList<>();

    @EventHandler
    public void death(PlayerDeathEvent e){

        if(e.getPlayer().getWorld().equals(Main.dream))
            e.setCancelled(true);

    }

    @EventHandler
    public void onBlock(BlockPlaceEvent e){
        if(e.getPlayer().getWorld().equals(Main.dream)) e.setCancelled(true);
    }

    @EventHandler
    public void interactWithFrames(PlayerItemFrameChangeEvent e) {
        if (e.getPlayer().getWorld().equals(Main.dream)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void quit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if( player.getWorld().equals(Main.dream) ){

            Dream.returnFromDream(player);

            quitedPlayers.add(player.getName());

            int id = Dream.returnTaskIdList.get(player.getName());
            Bukkit.getScheduler().cancelTask(id);

        }
        else if(Dream.playersLocations.containsKey(player.getName())){

            int id = Dream.tpTaskIdList.get(player.getName());
            Bukkit.getScheduler().cancelTask(id);

            quitedPlayers.add(player.getName());
        }

    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(quitedPlayers.contains(player.getName())){

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), ()->{
                new Dream().execute(player);
            }, 20L);
        }

    }
}
