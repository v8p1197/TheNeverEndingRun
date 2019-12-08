package it.unisa.theneverendingrun.config;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PropertiesCache {

    private static final Logger LOGGER = Logger.getLogger(PropertiesCache.class.getName());

    private final Properties configProp;

    private PropertiesCache() {
        configProp = new Properties();

        var in = this.getClass().getClassLoader().getResourceAsStream("app.properties");
        if (in == null) throw new NullPointerException();

        try {
            configProp.load(in);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            System.exit(1);
        }
    }

    //Bill Pugh Solution for singleton pattern
    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }

    public static PropertiesCache getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key){
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames(){
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key){
        return configProp.containsKey(key);
    }
}