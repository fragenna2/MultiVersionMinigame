package com.github.fragenna2.multiversion.listeners;

import com.github.fragenna2.multiversion.api.NMSHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private final NMSHandler handler;

    public JoinEvent(NMSHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onEnter(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        handler.test(player);
    }
}
