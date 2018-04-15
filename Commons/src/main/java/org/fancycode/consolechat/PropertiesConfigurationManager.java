package org.fancycode.consolechat;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Hello world!
 *
 */
public class PropertiesConfigurationManager implements IConfigurationManager
{
    private Configuration configuration;

    @Override
    public void readFromFile(String filename) {
        Parameters configurationBuildParameters = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class).
                        configure(configurationBuildParameters.properties().setFileName(filename));
        try {
            configuration = builder.getConfiguration();
        } catch (ConfigurationException e) {

        }
        configuration = null;
    }

    public <T> T getConfigurationOrDefault(Class<T> valueClass, String configurationKey, T defaultValue)
    {
        if (configuration != null)
            return configuration.get(valueClass, configurationKey, defaultValue);
        return defaultValue;
    }
}
