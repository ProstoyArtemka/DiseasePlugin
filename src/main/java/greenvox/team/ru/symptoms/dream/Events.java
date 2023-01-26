package greenvox.team.ru.symptoms.dream;

import greenvox.team.ru.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class Events implements Listener {

    private static ArrayList<String> quitedPlayers = new ArrayList<>();

    @EventHandler
    public void death(PlayerDeathEvent e){

        if(e.getPlayer().getWorld().equals(Main.dream))
            e.setCancelled(true);

    }

    @EventHandler
    public void quit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if( player.getWorld().equals(Main.dream) ){

            Dream.returnFromDream(player);

            quitedPlayers.add(player.getName());

            int id = Dream.taskIdList.get(player.getName());
            Bukkit.getScheduler().cancelTask(id);


        }

    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(quitedPlayers.contains(player.getName())){

            quitedPlayers.remove(player.getName());
            new Dream().execute(player);

        }

    }
}
