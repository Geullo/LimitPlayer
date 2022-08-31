package com.geullo.limitpeople.Events;

import com.geullo.limitpeople.LimitPeople;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class JoinPlayer implements Listener {
    private ConcurrentHashMap<UUID,Boolean> quitPlayers = new ConcurrentHashMap<>();

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent e){
        if (!e.getPlayer().isOp()&&Bukkit.getOnlinePlayers().stream().filter(p->!p.isOp()).collect(Collectors.toList()).size()> LimitPeople.maxPeople) {
            e.setJoinMessage("");
            quitPlayers.put(e.getPlayer().getUniqueId(),true);
            e.getPlayer().kickPlayer(ChatColor.WHITE+"서버 인원이 초과되어 서버에 참여할수 없습니다.");
        }
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent e){
        if (quitPlayers.containsKey(e.getPlayer().getUniqueId())) {
            e.setQuitMessage("");
            quitPlayers.remove(e.getPlayer().getUniqueId());
        }
    }
}
