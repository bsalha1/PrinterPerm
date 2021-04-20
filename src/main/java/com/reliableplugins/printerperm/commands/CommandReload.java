/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printerperm.commands;

import com.reliableplugins.printer.utils.BukkitUtil;
import com.reliableplugins.printerperm.Main;
import org.bukkit.command.CommandSender;

public class CommandReload extends Command
{
    public CommandReload()
    {
        super("reload", "printerperm.reload", "Reloads the PrinterPerm configs", false, new String[]{"r"});
    }

    @Override
    public void execute(CommandSender executor, String[] args)
    {
        Main.INSTANCE.reloadConfigs();
        executor.sendMessage(BukkitUtil.color("&dPrinterPerm has been reloaded"));
    }
}
