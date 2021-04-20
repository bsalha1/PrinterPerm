/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printerperm.commands;

import com.reliableplugins.printer.utils.BukkitUtil;
import com.reliableplugins.printerperm.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandHandler implements CommandExecutor
{
    private final Map<String, com.reliableplugins.printerperm.commands.Command> subCommands = new HashMap<>();
    private final String label;

    public CommandHandler(String label)
    {
        this.label = label;
        Main.INSTANCE.getCommand(label).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String string, String[] args) {

        if(args.length == 0)
        {
            commandSender.sendMessage(BukkitUtil.color("&c/printerperm reload&7, &c/printerperm version"));
            return true;
        }

        for (Command subcommand : subCommands.values())
        {
            // If argument isn't a subcommand or an alias of a subcommand, continue
            if(!args[0].equalsIgnoreCase(subcommand.getLabel()) && !subcommand.getAlias().contains(args[0].toLowerCase())) continue;

            // If player is required and they're not a player, throw error
            if(subcommand.isPlayerRequired() && !(commandSender instanceof Player))
            {
                commandSender.sendMessage(BukkitUtil.color("&cOnly players may execute this command"));
                return true;
            }

            // If first argument is a valid subcommand or is a subcommand alias, run command
            if(args[0].equalsIgnoreCase(subcommand.getLabel()) || subcommand.getAlias().contains(args[0].toLowerCase()))
            {
                // If subcommand doesn't have a permission node or the send has permission or they're an op, execute command
                if(!subcommand.hasPermission() || commandSender.hasPermission(subcommand.getPermission()) || commandSender.isOp())
                {
                    subcommand.execute(commandSender, Arrays.copyOfRange(args, 1, args.length));
                }
                else
                {
                    commandSender.sendMessage(BukkitUtil.color("&cInsufficient permissions"));
                }
                return true;
            }
        }

        // By here, the command entered isn't valid
        commandSender.sendMessage(BukkitUtil.color("&c/printerperm reload&7, &c/printerperm version"));
        return true;
    }

    public void addCommand(com.reliableplugins.printerperm.commands.Command command)
    {
        this.subCommands.put(command.getLabel().toLowerCase(), command);
    }

    public Collection<Command> getSubCommands()
    {
        return Collections.unmodifiableCollection(subCommands.values());
    }

    public String getLabel()
    {
        return label;
    }
}
