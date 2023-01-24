package greenvox.team.ru.symptoms.dream;

import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import greenvox.team.ru.Main;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Random;

public class Dream implements Symptom {

    private static HashMap<String, Integer> NpcIdList = new HashMap<>();
    public static HashMap<String, Integer> taskIdList = new HashMap<>();
    private static int id = 0;

    @Override
    public void init(JavaPlugin main) {}

    @Override
    public void execute(Player player) {
        NpcIdList.put(player.getName(), id);

        tpInDream(player);
        int timer = new Random(20).nextInt(60);

        int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), ()->
                returnFromDream(player), timer*20);


        taskIdList.put(player.getName(), taskId);
        

    }

    private static void tpInDream(Player player){
        Location location = player.getLocation();

        NPC.Global npc = NPCLib.getInstance().generateGlobalNPC(Main.getInstance(), String.valueOf(id), location);
        id++;

        npc.setSkin(player);
        npc.setPose(NPC.Pose.SLEEPING);
        npc.setShowOnTabList(false);
        npc.setSleeping(true);
        npc.update();

        int x = new Random(-5000).nextInt(5000);
        int z = new Random(-5000).nextInt(5000);

        player.teleport(new Location(Main.dream, x, 1, z));

    }

    public static void returnFromDream(Player player){

        int id = NpcIdList.get(player.getName());
        NPC.Global npc = NPCLib.getInstance().getGlobalNPC(Main.getInstance(), String.valueOf(id));
        NpcIdList.remove(player.getName());


        player.teleport(npc.getLocation());
        player.chat("/lay");
        npc.destroy();
    }

    @Override
    public int getLevel() {
        return 4;
    }
}

