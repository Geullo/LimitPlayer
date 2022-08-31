package com.geullo.limitpeople;

import com.geullo.limitpeople.Events.JoinPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class LimitPeople extends JavaPlugin {
    public final static Logger log = Logger.getLogger("Minecraft");
    public static Integer maxPeople = 10;
    public static LimitConfig limitConfig;
    private static LimitPeople plugin;

    public static LimitPeople getPlugin(){
        return plugin;
    }

    public static void log(String string, Level level) {
        log.log(level, "[RuTaeY Limit People Plugin]" + string);
    }

    @Override
    public void onEnable() {
        plugin = this;
        LimitConfig.initConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new JoinPlayer(),this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender executor, Command cmd, String label, String[] args) {
        if ("인원제한".equalsIgnoreCase(cmd.getName())) {
            Player sender = Bukkit.getPlayer(executor.getName());
            if (sender==null){
                if (args.length<1) {
                    executor.sendMessage("인원제한 설정 [설정할 인원수]");
                    executor.sendMessage("인원제한 확인");
                }
                if ("설정".equalsIgnoreCase(args[0])||"set".equalsIgnoreCase(args[0])){
                    if (args.length<2){
                        executor.sendMessage("인원제한 설정 [설정할 인원수]");
                    }
                    limitConfig.setMaxPlayer(Integer.parseInt(args[1]));
                    executor.sendMessage("인원 제한을 "+ limitConfig.getMaxPlayer()+"명으로 설정했습니다.");
                }
                else if ("확인".equalsIgnoreCase(args[0])||"check".equalsIgnoreCase(args[0])){
                    executor.sendMessage("현재 제한된 인원 제한수 : "+ChatColor.GOLD+ChatColor.BOLD+ limitConfig.getMaxPlayer()+ChatColor.WHITE+"명");
                }
            }
            else {
                if (!sender.isOp()){
                    sender.sendMessage("Unknown command. Type \"/help\" for help.");
                    return true;
                }
                if (args.length<1) {
                    sender.sendMessage("/인원제한 설정 [설정할 인원수]");
                    sender.sendMessage("/인원제한 확인");
                }
                if ("설정".equalsIgnoreCase(args[0])||"set".equalsIgnoreCase(args[0])){
                    if (args.length<2){
                        sender.sendMessage("/인원제한 설정 [설정할 인원수]");
                    }
                    limitConfig.setMaxPlayer(Integer.parseInt(args[1]));
                    sender.sendMessage("인원 제한을 "+ limitConfig.getMaxPlayer()+"명으로 설정했습니다.");
                }
                else if ("확인".equalsIgnoreCase(args[0])||"check".equalsIgnoreCase(args[0])){
                    limitConfig.setMaxPlayer(Integer.parseInt(args[1]));
                    sender.sendMessage("현재 제한된 인원 제한수 : "+ChatColor.GOLD+ChatColor.BOLD+ limitConfig.getMaxPlayer()+ChatColor.WHITE+"명");
                }
            }

        }
        return true;
    }
}
