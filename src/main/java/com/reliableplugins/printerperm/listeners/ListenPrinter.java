/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printerperm.listeners;

import com.reliableplugins.printer.PrinterPlayer;
import com.reliableplugins.printerperm.Main;
import com.reliableplugins.printer.api.PrinterOffEvent;
import com.reliableplugins.printer.api.PrinterOnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ListenPrinter implements Listener
{
    @EventHandler
    public void onPrinterOn(PrinterOnEvent event)
    {
        PrinterPlayer player = event.getPrinterPlayer();
        Main.INSTANCE.getPermission().playerAdd(player.getPlayer(), Main.INSTANCE.getMainConfig().getPermission());
    }

    @EventHandler
    public void onPrinterOff(PrinterOffEvent event)
    {

        PrinterPlayer player = event.getPrinterPlayer();
        Main.INSTANCE.getPermission().playerRemove(player.getPlayer(), Main.INSTANCE.getMainConfig().getPermission());
    }
}
