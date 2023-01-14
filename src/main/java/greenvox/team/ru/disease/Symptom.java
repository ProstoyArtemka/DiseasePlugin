package greenvox.team.ru.disease;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface Symptom {

    void init(JavaPlugin main);
    void execute(Player player);

    int getLevel();
}
