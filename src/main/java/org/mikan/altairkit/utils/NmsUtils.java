package org.mikan.altairkit.utils;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
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
    public static void packetPlayOutParticle(Location location, EnumParticle particle,float speed,int count) {

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


}
