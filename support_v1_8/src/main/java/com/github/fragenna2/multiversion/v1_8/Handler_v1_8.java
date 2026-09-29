package com.github.fragenna2.multiversion.v1_8;

import com.github.fragenna2.multiversion.api.NMSHandler;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Handler_v1_8 implements NMSHandler {

    private EntityPlayer npc;

    @Override
    public void spawnNpc() {

        org.bukkit.World bukkitWorld = Bukkit.getWorlds().get(0);
        WorldServer nmsWorld = ((CraftWorld) bukkitWorld).getHandle();
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();

        UUID npcUuid = UUID.randomUUID();
        GameProfile gameProfile = new GameProfile(npcUuid, "NPC_1_8");

        this.npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));

        // Set NPC initial position (x, y, z, yaw, pitch)
        Location loc = bukkitWorld.getSpawnLocation();
        this.npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    @Override
    public void showNpc(UUID playerId) {
        if (this.npc == null) return;

        Player target = Bukkit.getPlayer(playerId);
        if (target == null || !target.isOnline()) return;

        PlayerConnection connection = ((CraftPlayer) target).getHandle().playerConnection;

        PacketPlayOutPlayerInfo infoPacket = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,
                this.npc
        );
        connection.sendPacket(infoPacket);

        PacketPlayOutNamedEntitySpawn spawnPacket = new PacketPlayOutNamedEntitySpawn(this.npc);
        connection.sendPacket(spawnPacket);

        // 3. Update Head Rotation & Body Yaw
        PacketPlayOutEntityHeadRotation headRotationPacket = new PacketPlayOutEntityHeadRotation(
                this.npc,
                (byte) (this.npc.yaw * 256.0F / 360.0F)
        );
        connection.sendPacket(headRotationPacket);

        // Remove NPC from TabList
        Bukkit.getScheduler().runTaskLater(
                Bukkit.getPluginManager().getPlugin("YourPluginName"),
                () -> {
                    PacketPlayOutPlayerInfo removeInfo = new PacketPlayOutPlayerInfo(
                            PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
                            this.npc
                    );
                    connection.sendPacket(removeInfo);
                },
                40L
        );
    }

    @Override
    public void hide(UUID playerId) {
        if (this.npc == null) return;

        Player target = Bukkit.getPlayer(playerId);
        if (target == null || !target.isOnline()) return;

        PlayerConnection connection = ((CraftPlayer) target).getHandle().playerConnection;

        PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(this.npc.getId());
        connection.sendPacket(destroyPacket);
    }

    @Override
    public void destroyNpc(int id) {
        // Destroy NPC packet broadcast to all online players
        PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(id);

        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroyPacket);
        }

        if (this.npc != null && this.npc.getId() == id) {
            this.npc = null;
        }
    }

    @Override
    public void test(Player player) {
        if (player == null) return;

        spawnNpc();
        showNpc(player.getUniqueId());
        player.sendMessage("§a[1.8.8] NPC spawned successfully!");
    }
}