package org.mikan.altairkit;


import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.mikan.altairkit.api.commands.AltairCommand;
import org.mikan.altairkit.api.gui.Data;
import org.mikan.altairkit.api.gui.InventoryClickEventListener;
import org.mikan.altairkit.api.gui.InventoryCloseEventListener;
import org.mikan.altairkit.api.json.AltairGsonFactory;
import org.mikan.altairkit.api.json.JsonFile;
import org.mikan.altairkit.api.json.JsonSection;
import org.mikan.altairkit.utils.CmdMap;
import org.mikan.altairkit.utils.PlayerHead;

import java.util.UUID;


public final class AltairKit extends JavaPlugin {

    public static AltairKit getInstance() {
        return INSTANCE;
    }

    private static AltairKit INSTANCE;
    public  static  JsonFile file ;
    @Override
    public void onEnable() {
        // Testing only

        INSTANCE = this;

        enableGUIManager(this);

        JsonFile file = new JsonFile(this,"test");
        file.set("t1.t2.t3.t4.t5","END");

    }


    public static void enableGUIManager(JavaPlugin plugin){
        Data.listeningToAltairGUIs = true;
        Bukkit.getPluginManager().registerEvents(new InventoryClickEventListener(),plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseEventListener(),plugin);
    }


    public static void disableGUIManager(JavaPlugin plugin){
        Data.listeningToAltairGUIs = false;
        Bukkit.getPluginManager().disablePlugin(plugin);
        Bukkit.getPluginManager().enablePlugin(plugin);
    }


    public static void registerCommand(AltairCommand command){
        CmdMap.getCommandMap().register("", command);
    }

    public static ItemStack getPlayerHead(Player player){
        try {
            return new PlayerHead(player).getSkullItem();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemStack getPlayerHead(OfflinePlayer player){
        try {
            return new PlayerHead(player).getSkullItem();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemStack getPlayerHead(UUID uuid){
        try {
            return new PlayerHead(uuid).getSkullItem();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Gson buildGson(){
        return AltairGsonFactory.createGson();
    }

    public static String colorize(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }

}
