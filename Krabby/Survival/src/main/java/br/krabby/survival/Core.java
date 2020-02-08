package br.krabby.survival;

import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    /*
        Storage tables:
            jogadores:
                name,
                uuid,
                ipaddress,
                info:
                    money,
                    skin,
                    group,
                    permissions:[{
                        name,
                        expireat
                    }],
                    currentmute,
                    currentban,
                    onlinetime,
                    friends,
                    ignoredplayers,
                    mailbox:{
                        inbox:[{
                            sender,
                            message,
                            items,
                            timestamp
                        }],
                        sent:[{
                            receiver,
                            message,
                            items,
                            timestamp
                        }]
                    }
                auth:
                    password,
                    email,
                    registeredat,
                    changedpasswordat,
                    lastloginat
                mutehistory:[{
                    staffer,
                    reason,
                    expireat,
                    timestamp
                }]
                banhistory:[{
                    staffer,
                    reason,
                    expireat,
                    timestamp
                }]
            skins:
                name,
                uuid,
                value,
                signature,
                timestamp

    */
    public void onEnable(){
        Group.initialize();
    }

    public static Core getInstance(){
        return getPlugin(Core.class);
    }
}