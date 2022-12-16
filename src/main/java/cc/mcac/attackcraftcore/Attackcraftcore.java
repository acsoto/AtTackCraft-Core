package cc.mcac.attackcraftcore;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class Attackcraftcore extends Plugin {

    public static Attackcraftcore plugin = null;
    private Configuration configuration;

    @Override
    public void onEnable() {
        plugin = this;
        loadConfig();
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Announce());
        if (getProxy().getPluginManager().getPlugin("MiraiMC") != null) {
            getProxy().getPluginManager().registerListener(this, new miraiMC(this));
            getLogger().info("接入 miraiMC");
            return;
        }
        new PlayerListLogger(this).run();
        getLogger().info("AtTackCraft-Core 已启动");
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfig() {
        try {
            if (!getDataFolder().exists())
                getDataFolder().mkdir();

            File file = new File(getDataFolder(), "config.yml");

            if (!file.exists()) {
                try (InputStream in = getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).
                    load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
