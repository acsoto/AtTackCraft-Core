package cc.mcac.attackcraftcore.Bukkit;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TaoYuanProtection implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getWorld().getName().equals("sc")) {
            if (e.getPlayer().hasPermission("multiverse.access.sc")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + e.getPlayer().getName() + " permission unset multiverse.access.sc");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + e.getPlayer().getName() + " parent add taoyuan");
            }
            if (e.getPlayer().hasPermission("mcac.taoyuan")) {
                return;
            }
            e.getPlayer().sendMessage("§c您还没有§d桃源居住证§c, 不可在该世界进行此操作");
            e.setCancelled(true);
        }
    }
}
