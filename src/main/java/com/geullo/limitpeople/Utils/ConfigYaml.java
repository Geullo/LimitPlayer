package com.geullo.limitpeople.Utils;

import com.geullo.limitpeople.LimitPeople;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class ConfigYaml {
    public File file;
    public ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();
    public FileConfiguration config;
    public final int intFalse = -99999;
    public ConfigYaml(){}
    @Deprecated
    public void setUp(){
        try {
            if (file == null) return;
            if (!file.exists()) {
                file.createNewFile();
            }
            config = YamlConfiguration.loadConfiguration(file);
        }catch (IOException e){
            LimitPeople.log(e.getMessage(), Level.WARNING);
            e.printStackTrace();
        }
    }
    public void save(){
        try {
            config.save(file);
            reload();
        }catch (IOException e){
            LimitPeople.log(e.getMessage(), Level.WARNING);
            e.printStackTrace();
        }
    }
    public void clear(){
        try {
            file.delete();
            file.createNewFile();
        }catch (IOException e){
            LimitPeople.log(e.getMessage(), Level.WARNING);
            e.printStackTrace();
        }
    }
    public void delete() {
        file.delete();
    }
    public void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setData(String path,Object data) {
        reload();
        config.set(path, data);
        save();
    }

    public String getString(String key){
        reload();
        return config.contains(key)?config.getString(key):"";
    }
    public int getInt(String key){
        reload();
        return config.contains(key)?config.getInt(key):intFalse;
    }
    public Object getObject(String key){
        reload();
        return config.contains(key)?config.get(key):null;
    }
    public ItemStack getItemStack(String key){
        reload();
        return config.contains(key)?config.getItemStack(key):new ItemStack(Material.AIR);
    }

}
