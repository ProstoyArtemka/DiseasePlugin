package greenvox.team.ru.commands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.NeedsOp;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.disease.DiseaseManager;
import greenvox.team.ru.disease.Symptom;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("disease")
@Alias("infection")
@NeedsOp
public class DiseaseCommand {

    @Subcommand("apply")
    @NeedsOp
    public static void apply(CommandSender sender, @APlayerArgument Player target) {
        DatabaseManager.applyDiseaseToPlayer(target);
    }

    @Subcommand("apply")
    @NeedsOp
    public static void apply(Player player) {
        DatabaseManager.applyDiseaseToPlayer(player);
    }

    @Subcommand("remove")
    @NeedsOp
    public static void remove(CommandSender sender, @APlayerArgument Player target) {
        DatabaseManager.removeDiseaseFromPlayer(target);
    }

    @Subcommand("remove")
    @NeedsOp
    public static void remove(Player player) {
        DatabaseManager.removeDiseaseFromPlayer(player);
    }

    @Subcommand("level")
    @NeedsOp
    public static void level(CommandSender sender, @APlayerArgument Player target, @AIntegerArgument int level) {
        DatabaseManager.setDiseaseLevelToPlayer(target, level);
    }

    @Subcommand("level")
    @NeedsOp
    public static void level(Player player, @AIntegerArgument int level) {
        DatabaseManager.setDiseaseLevelToPlayer(player, level);
    }

    @Subcommand("execute")
    @NeedsOp
    public static void execute(Player player, @AStringArgument String name) {
        for (Symptom s : DiseaseManager.Symptoms) {
            if (s.getClass().getName().toLowerCase().split("\\.")[4].equals(name))
                s.execute(player);
        }
    }

    @Subcommand("execute")
    @NeedsOp
    public static void execute(CommandSender sender, @APlayerArgument Player target, @AStringArgument String name) {
        for (Symptom s : DiseaseManager.Symptoms) {
            if (s.getClass().getName().toLowerCase().split("\\.")[4].equals(name))
                s.execute(target);
        }
    }

    public static void init() {
        CommandAPI.registerCommand(DiseaseCommand.class);
    }
}
