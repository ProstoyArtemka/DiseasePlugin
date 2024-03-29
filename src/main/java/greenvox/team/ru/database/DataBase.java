package greenvox.team.ru.database;


import greenvox.team.ru.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataBase {
    private final File file;
    private final FileConfiguration config;

    public DataBase(String name){
        file = new File(Main.getInstance().getDataFolder(), name);

        try {
            if (!file.exists() && !file.createNewFile())
                throw new IOException();

        } catch (IOException e) {
            throw new RuntimeException("Не получилось создать файл", e);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig(){
        return config;
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Не получилось сохранить файл", e);
        }
    }
    public static void write(String path, Object object){
        Main.getData().getConfig().set(path, object);
        Main.getData().save();
    }

    public static void write(String path, int data){
        Main.getData().getConfig().set(path, data);
        Main.getData().save();
    }

    public static void delete(String path){
        Main.getData().getConfig().set(path, null);
        Main.getData().save();
    }

}
