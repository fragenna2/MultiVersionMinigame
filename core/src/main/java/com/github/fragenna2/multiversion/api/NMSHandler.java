package com.github.fragenna2.multiversion.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public interface NMSHandler {

    void spawnNpc();
    void showNpc(UUID playerId);
    void hide(UUID playerId);
    void destroyNpc(int id);

    void test(Player player);
}
