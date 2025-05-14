package org.mikan.altairkit.utils;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.logging.Logger;

public abstract class Module {

    private final static List<Module> modules = new ArrayList<>();
    protected Set<Listener> listeners;

    protected final Plugin plugin;

    private final String name;
    private final Logger logger;
    protected boolean loaded = false;
    private final ChatColor color;

    private final String PL_NAME;

    public Module(Plugin plugin, String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Module name cannot be blank");
        }

        this.plugin = plugin;
        this.PL_NAME = plugin.getName();
        this.name = name;
        this.color = ChatColor.WHITE;
        this.logger = plugin.getLogger();

    }

    public Module(Plugin plugin, String name, ChatColor color) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Module name cannot be blank");
        }

        this.plugin = plugin;
        this.PL_NAME = plugin.getName();
        this.name = name;
        this.color = color;
        this.logger = plugin.getLogger();

    }

    protected void listen(Listener... listeners){
        this.listeners = new HashSet<> (Arrays.asList(listeners));
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener,plugin);
        }
    }

    protected void listen(Set<Listener>  listeners){
        this.listeners = listeners;
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener,plugin);
        }
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void loadConfig();


    public abstract void registerCommands(Plugin plugin);
    public abstract void registerListeners(Plugin plugin);

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public void info(String message){
        Bukkit.getConsoleSender().sendMessage(color + "["+PL_NAME+" -> "+name+"] " + message);
    }

    public void info(String message,Class<?> type){
        Bukkit.getConsoleSender().sendMessage(color + "["+PL_NAME+" -> "+name+":"+type.getName()+"] " + message);
    }

    public void error(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"["+PL_NAME+" -> "+name+"]:ERROR " + message);
    }

    public void warning(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW+"["+PL_NAME+" -> "+name+"]:WARNING " + message);
    }

    public Set<Listener> getListeners() {
        return listeners;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getName() {
        return name;
    }

    public Logger getLogger() {
        return logger;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getPL_NAME() {
        return PL_NAME;
    }
}



