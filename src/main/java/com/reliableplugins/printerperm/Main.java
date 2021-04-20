/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printerperm;

import com.reliableplugins.printerperm.commands.CommandHandler;
import com.reliableplugins.printerperm.commands.CommandReload;
import com.reliableplugins.printerperm.commands.CommandVersion;
import com.reliableplugins.printerperm.config.FileManager;
import com.reliableplugins.printerperm.config.MainConfig;
import com.reliableplugins.printerperm.listeners.ListenPrinter;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin
{
    public static Main INSTANCE;
    private String version;

    // Hooks
    private Permission permission;
    private CommandHandler commandHandler;

    // Configs
    private FileManager fileManager;
    private MainConfig mainConfig;

    @Override
    public void onEnable()
    {
        Main.INSTANCE = this;
        this.version = getDescription().getVersion();

        try
        {
            this.fileManager = setupConfigs();
            setupCommands();
            setupPermissions();
            setupListeners();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        getLogger().log(Level.INFO, this.getDescription().getName() + " v" + this.version + " has been loaded");
    }

    @Override
    public void onDisable()
    {
        getLogger().log(Level.INFO, this.getDescription().getName() + " v" + this.getDescription().getVersion() + " has been unloaded");
    }

    private void setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
        if (permissionProvider != null)
        {
            permission = permissionProvider.getProvider();
        }
        else
        {
            getLogger().log(Level.SEVERE, "Failed to find permission provider");
        }

        if(permission == null)
        {
            getLogger().log(Level.SEVERE, "Failed to get permission provider");
        }
    }
    private FileManager setupConfigs()
    {
        com.reliableplugins.printerperm.config.FileManager fileManager = new com.reliableplugins.printerperm.config.FileManager();
        fileManager.addFile(this.mainConfig = new com.reliableplugins.printerperm.config.MainConfig());

        return fileManager;
    }

    private void setupListeners()
    {
        Bukkit.getPluginManager().registerEvents(new ListenPrinter(), this);
    }

    private void setupCommands()
    {
        this.commandHandler = new CommandHandler("printer");
        this.commandHandler.addCommand(new CommandReload());
        this.commandHandler.addCommand(new CommandVersion());
    }

    public String getVersion()
    {
        return this.version;
    }

    public void reloadConfigs()
    {
        this.fileManager = setupConfigs();
    }

    public MainConfig getMainConfig()
    {
        return this.mainConfig;
    }

    public Permission getPermission()
    {
        return permission;
    }
}
