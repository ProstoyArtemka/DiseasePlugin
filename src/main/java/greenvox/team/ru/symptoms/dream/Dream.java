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

    public static HashMap<String, Integer> NpcIdList = new HashMap<>();
    public static HashMap<String, Integer> taskIdList = new HashMap<>();
    public static HashMap<String, Location> playersLocations = new HashMap<>();
    public static int id = 0;

    @Override
    public void init(JavaPlugin main) {}

    @Override
    public void execute(Player player) {
        if(!player.isOnGround()) return;
        if( new Random().nextInt(1, 3) != 1) return;

        FreezePlayerScheduler.freeze(player, 100);
        ServerEmoteAPI.forcePlayEmote(player.getUniqueId(),
                ServerEmoteAPI.getEmote(UUID.fromString("ce18f311-0000-0000-0000-000000000000")));
        new DreamScheduler(player).runTaskLater(Main.getInstance(), 100);

    }


    public static void tpInDream(Player player){
        Location location = player.getLocation();

        playersLocations.put(player.getName(), location);

        NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(Main.getInstance(), String.valueOf(id), location);
        id++;

        npc.setSkin(player);
        npc.setPose(NPC.Pose.SLEEPING);
        npc.setShowOnTabList(false);
        npc.setSleeping(true);
        npc.update();

        int x = new Random().nextInt(5000);
        int z = new Random().nextInt(5000);

        player.teleport(new Location(Main.dream, x, 1, z));
        particle(player);

    }

    public static void returnFromDream(Player player){

        int id = NpcIdList.get(player.getName());
        NPC.Global npc = NPCLib.getInstance().getGlobalNPC(Main.getInstance(), String.valueOf(id));

        NpcIdList.remove(player.getName());
        playersLocations.remove(player.getName());

        player.teleport(npc.getLocation());
        player.chat("/lay");

        npc.destroy();
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

