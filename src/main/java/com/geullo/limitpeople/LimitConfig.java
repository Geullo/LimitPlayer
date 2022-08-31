package com.geullo.limitpeople;

import com.geullo.limitpeople.Utils.ConfigYaml;

import java.io.File;

public class LimitConfig extends ConfigYaml {
    public LimitConfig(){
        file = new File(LimitPeople.getPlugin().getDataFolder(),"limit.yml");
        setUp();
        config.options().copyDefaults(true);
        save();
    }
    public static void initConfig() {
        File config = new File(LimitPeople.getPlugin().getDataFolder(), "limit.yml");
        if (!config.exists()){
            LimitPeople.limitConfig = new LimitConfig();
            LimitPeople.limitConfig.setMaxPlayer(10);
            LimitPeople.maxPeople = 10;
        }else {
            LimitPeople.limitConfig = new LimitConfig();
            LimitPeople.maxPeople = LimitPeople.limitConfig.getMaxPlayer();
        }
    }

    public Integer getMaxPlayer(){
        return getInt("MAX_PLAYER");
    }
    public void setMaxPlayer(int limit){
        LimitPeople.maxPeople = limit;
        setData("MAX_PLAYER",limit);
    }



}
