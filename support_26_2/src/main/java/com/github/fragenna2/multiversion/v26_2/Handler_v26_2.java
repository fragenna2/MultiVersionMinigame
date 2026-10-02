package com.github.fragenna2.multiversion.v26_2;

import com.github.fragenna2.multiversion.api.NMSHandler;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Handler_v26_2 implements NMSHandler {

    @Override
    public void spawnNpc(org.bukkit.entity.Player player) {

    }

    @Override
    public void showNpc(org.bukkit.entity.Player player) {

    }

    @Override
    public void hide(Player player) {

    }

    @Override
    public void destroyNpc(int id) {

    }

    @Override
    public void test(org.bukkit.entity.Player player) {
        player.sendRichMessage("<green>Version: 26.2");
    }
}