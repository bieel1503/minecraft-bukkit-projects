package br.bieel.storage.utils;

import org.bukkit.scheduler.BukkitRunnable;

import br.bieel.storage.StorageHolder;

public abstract class Callable {
    abstract public void call();
    public void mainThreadCall(){
        new BukkitRunnable(){
            public void run(){
                call();
            }
        }.runTask(StorageHolder.getInstance().getPlugin());
    }
}