package com.demo.filler;

import com.demo.dto.ConnectionConfigDTO;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

import static com.demo.Main.PROPERTY_FILE_NAME;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class ApplicationProperties {

    private final static ApplicationProperties applicationPropertiesInstance = new ApplicationProperties();
    private ConnectionConfigDTO connectionConfig;

    private ApplicationProperties() {
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(PROPERTY_FILE_NAME);

        try {
            connectionConfig = yaml.loadAs(inputStream, ConnectionConfigDTO.class);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public ConnectionConfigDTO getConnectionConfig() {
        return connectionConfig;
    }

    public static ApplicationProperties getApplicationPropertiesInstance() {
        return applicationPropertiesInstance;
    }
}
