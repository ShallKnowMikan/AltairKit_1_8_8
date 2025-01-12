package org.mikan.altairkit.utils;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NmsUtils {

    /**
     * Quester classe serve per mandare una
     * ActionBar tramite l'NMS
     *
     * @param player  Il player a cui verrá inviata
     * @param message Il messaggio che conterrá
     */
    public static void sendActionBar(Player player, String message) {
        IChatBaseComponent chatComponent = new ChatComponentText(message);
        PacketPlayOutChat packet = new PacketPlayOutChat(chatComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    /**
     * Serve per manifestare un effetto.
     *
     * @param location La posizione dove si manifesterá l'effetto
     */
    public static void sendParticles(Location location, EnumParticle particle, float speed, int count) {

        try {

            float x = (float) location.getX();
            float y = (float) location.getY();
            float z = (float) location.getZ();
            float offsetX = 0;
            float offsetY = 0;
            float offsetZ = 0;


            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    particle,
                    true,
                    x, y, z,
                    offsetX, offsetY, offsetZ,
                    speed,
                    count
            );

            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (Exception ignored) {

        }
    }

    public static void sendParticles(float x , float y , float z, EnumParticle particle, float speed, int count) {

        try {

            float offsetX = 0;
            float offsetY = 0;
            float offsetZ = 0;


            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    particle,
                    true,
                    x, y, z,
                    offsetX, offsetY, offsetZ,
                    speed,
                    count
            );

            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (Exception ignored) {

        }
    }


    public static void sendColorizedParticles(Location location, Color color, float speed, int count) {

        try {

            float x = (float) location.getX();
            float y = (float) location.getY();
            float z = (float) location.getZ();

            float r = color.getRed() / 255.0f;
            float g = color.getGreen() / 255.0f;
            float b = color.getBlue() / 255.0f;

            // r can't be 0
            if (r == 0) {
                r = Float.MIN_VALUE;
            }

            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    EnumParticle.REDSTONE, true, x, y, z, r, g, b, speed, count);

            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (Exception ignored) {

        }
    }

    public static void sendColorizedParticles(float x,float y,float z, Color color, float speed) {

        try {


            float r = color.getRed() / 255.0f;
            float g = color.getGreen() / 255.0f;
            float b = color.getBlue() / 255.0f;

            // r can't be 0
            if (r == 0) {
                r = Float.MIN_VALUE;
            }

            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    EnumParticle.REDSTONE, true, x, y, z, r, g, b, speed, 0);

            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (Exception ignored) {

        }
    }


}
