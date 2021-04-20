/*
 * Project: Printer
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.printerperm.config;

public class MainConfig extends Config
{
    private String permission;

    public MainConfig()
    {
        super("config.yml");
    }

    @Override
    public void load()
    {
        permission = getString("permission", "spartan.bypass");
        save();
    }

    public String getPermission()
    {
        return permission;
    }
}
