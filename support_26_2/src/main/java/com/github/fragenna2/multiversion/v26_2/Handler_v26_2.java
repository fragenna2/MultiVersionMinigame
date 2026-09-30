package com.github.fragenna2.multiversion.v26_2;

import com.github.fragenna2.multiversion.api.NMSHandler;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Handler_v26_2 implements NMSHandler {

    @Override
    public void spawnNpc() {

    }

    @Override
    public void showNpc(UUID playerId) {

    }

    @Override
    public void hide(UUID playerId) {

    }

    @Override
    public void destroyNpc(int id) {

    }

    @Override
    public void test(org.bukkit.entity.Player player) {
        player.sendRichMessage("<green>Version: 26.2");
    }
}