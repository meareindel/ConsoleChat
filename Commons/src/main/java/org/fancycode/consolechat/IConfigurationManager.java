package org.fancycode.consolechat;

public interface IConfigurationManager {
    public void readFromFile(String filename);
    <T> T getConfigurationOrDefault(Class<T> valueClass, String configurationKey, T defaultValue);
}
