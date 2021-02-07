package com.github.ventuss.manager.config;

import com.github.ventuss.config.GlobalConfiguration;
import com.github.ventuss.config.SqlConfig;
import com.github.ventuss.config.TotemConfiguration;
import com.github.ventuss.manager.IManager;

public class ConfigManager implements IManager {

    public GlobalConfiguration globalConfiguration;
    public TotemConfiguration totemConfiguration;
    public SqlConfig sqlConfig;

    public ConfigManager() {
        globalConfiguration = new GlobalConfiguration();
        totemConfiguration = new TotemConfiguration();
        sqlConfig = new SqlConfig();
    }
}
