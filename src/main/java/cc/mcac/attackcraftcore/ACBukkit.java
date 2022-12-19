package cc.mcac.attackcraftcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ACBukkit extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadPlaceholderAPI();
        loadCommandsOnEnable();
        getLogger().info("启动成功");
    }

    @Override
    public void onDisable() {
    }

    private void loadPlaceholderAPI() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().info("已接入PlaceholderAPI");
        }
    }

    private void loadCommandsOnEnable() {
        List<String> commands = getConfig().getStringList("bukkit.commands_on_enable");
        if (commands == null || commands.isEmpty()) {
            return;
        }
        getLogger().info("正在执行BukkitOnEnable指令");
        for (String command : commands) {
            getLogger().info("执行指令: " + command);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }
}