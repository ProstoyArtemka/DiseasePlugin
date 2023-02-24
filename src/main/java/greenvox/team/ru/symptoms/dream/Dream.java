package greenvox.team.ru.symptoms.dream;

import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import greenvox.team.ru.Main;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.FreezePlayerScheduler;
import greenvox.team.ru.util.SchedulerManager;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Dream  implements Symptom{

    public static HashMap<String, Integer> returnTaskIdList = new HashMap<>();
    public static HashMap<String, Integer> tpTaskIdList = new HashMap<>();
    public static HashMap<String, Location> playersLocations = new HashMap<>();

    @Override
    public void init(JavaPlugin main) {}

    @Override
    public void execute(Player player) {
        if(!player.isOnGround()) return;

        if(!Events.quitedPlayers.contains(player.getName())) {
            if (new Random().nextInt(1, 3) != 1) return;
        }
        else Events.quitedPlayers.remove(player.getName());

        Location location = player.getLocation();
        playersLocations.put(player.getName(), location);

        FreezePlayerScheduler.freeze(player, 99);
        ServerEmoteAPI.forcePlayEmote(player.getUniqueId(),
                ServerEmoteAPI.getEmote(UUID.fromString("ce18f311-0000-0000-0000-000000000000")));

        int id = new DreamScheduler(player).runTaskLater(Main.getInstance(), 100).getTaskId();
        tpTaskIdList.put(player.getName(), id);

    }


    public static void tpInDream(Player player){


        int x = new Random().nextInt(500);
        int z = new Random().nextInt(500);

        player.teleport(new Location(Main.dream, x, 1, z));
        particle(player);

    }

    public static void returnFromDream(Player player){


        player.teleport(playersLocations.get(player.getName()));
        playersLocations.remove(player.getName());
        player.chat("/lay");

    }

    public static void particle(Player player){

        Location loc = player.getLocation();
        loc = loc.getBlock().getLocation();

        ArrayList<Location> locations = new ArrayList<>();

        for(int i = 1; i <= new Random().nextInt(5,15); i++){
            int x = new Random().nextInt(-20, 20);
            int z = new Random().nextInt(-20, 20);

            Location particleLoc = new Location(player.getWorld(), loc.getX()+x, loc.getY(), loc.getZ()+z);
            locations.add(particleLoc);
        }

        SchedulerManager.runTaskTimer("Dream.particle." + player.getName(), new BukkitRunnable() {
            int count = 1;
            @Override
            public void run() {
                for(Location location: locations){

                    player.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE,
                            location.add(0, 0.2, 0),
                            4, new org.bukkit.Particle.DustOptions(Color.GREEN, 1));

                    if(count % 50 == 0){
                        int x = new Random().nextInt(-20, 20);
                        int z = new Random().nextInt(-20, 20);
                        location.setX(player.getLocation().getX()+x);
                        location.setZ(player.getLocation().getZ()+z);
                        location.setY(0);
                    }
                }
                count++;

            }
        }, 0, 1);

    }


    public static void onDisable(){

        for (Player player: Main.dream.getPlayers()){
            Location location = playersLocations.get(player.getName());
            player.teleport(location);

        }


    }


    @Override
    public int getLevel() {
        return 6;
    }
}

