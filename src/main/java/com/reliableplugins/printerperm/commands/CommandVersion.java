/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printerperm.commands;

import com.reliableplugins.printer.utils.BukkitUtil;
import com.reliableplugins.printerperm.Main;
import org.bukkit.command.CommandSender;

public class CommandVersion extends Command
{
    public CommandVersion()
    {
        super("version", "printerperm.version", "Get current version of PrinterPerm", false, new String[]{"v"});
    }

    @Override
    public void execute(CommandSender executor, String[] args)
    {
        executor.sendMessage(BukkitUtil.color("&dPrinterPerm &8v" + Main.INSTANCE.getVersion()));
    }
}
