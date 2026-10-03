package com.github.fragenna2.multiversion.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface NPC {

    String getName();

    Location getLocation();

    void spawn();

    void show(Player player);

    void hide(Player player);

    UUID getUUID();

    void delete();
}
