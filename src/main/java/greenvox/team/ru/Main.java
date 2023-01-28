package greenvox.team.ru;

import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.sergiferry.playernpc.api.NPCLib;
import greenvox.team.ru.commands.DiseaseCommand;
import greenvox.team.ru.database.DataBase;
import greenvox.team.ru.disease.DiseaseManager;
import greenvox.team.ru.events.UseAtAnotherPlayer;
import greenvox.team.ru.events.onPillsEatEvent;
import greenvox.team.ru.recipes.MaskRecipe;
import greenvox.team.ru.recipes.PillsRecipe;
import greenvox.team.ru.recipes.SyringeRecipe;
import greenvox.team.ru.symptoms.dream.CustomChunkGenerator;
import greenvox.team.ru.symptoms.dream.Dream;
import greenvox.team.ru.symptoms.dream.Events;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Main Instance;
    private static DataBase Data;
    public static World dream;

    @Override
    public void onEnable() {
        Instance = this;
        saveDefaultConfig();

        NPCLib.getInstance().registerPlugin(Instance);

        WorldCreator world = new WorldCreator("dream");
        world.generator(new CustomChunkGenerator());
        world.generateStructures(false);
        world.environment(World.Environment.NETHER);
        world.type(WorldType.FLAT);

        dream = Bukkit.createWorld(world);

        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new UseAtAnotherPlayer(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new onPillsEatEvent(), this);


        Data = new DataBase("database.yml");

        DiseaseManager.initSymptoms();
        DiseaseManager.runDiseaseTimer();

        DiseaseCommand.init();

        registerCrafts();

//        HashMap<UUID, KeyframeAnimation> emotes = ServerEmoteAPI.getLoadedEmotes();
//        Logger log = Bukkit.getLogger();
//        log.info(emotes.toString());



    }

    private void registerCrafts() {
        if (Bukkit.getRecipe(MaskRecipe.MaskKey) == null) Bukkit.addRecipe(new MaskRecipe());
        if (Bukkit.getRecipe(SyringeRecipe.SyringeKey) == null) Bukkit.addRecipe(new SyringeRecipe());
        if (Bukkit.getRecipe(PillsRecipe.PillsKey) == null) Bukkit.addRecipe(new PillsRecipe());
    }

    @Override
    public void onDisable() {
        Dream.onDisable();

    }

    public static Main getInstance() {
        return Instance;
    }
    public static DataBase getData(){
        return Data;
    }
}
