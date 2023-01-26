package greenvox.team.ru.symptoms.dream;

import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import greenvox.team.ru.Main;
import greenvox.team.ru.database.DataBase;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.FreezePlayer;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

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

        FreezePlayer.freeze(player, 100);
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


    public static void onDisable(){

        for (Player player: Main.dream.getPlayers()){
            Location location = playersLocations.get(player.getName());
            player.teleport(location);

        }


    }


    @Override
    public int getLevel() {
        return 4;
    }
}

