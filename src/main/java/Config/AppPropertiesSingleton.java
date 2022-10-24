package Config;

import Models.YamlClasses.EnvironmentModel;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public final class AppPropertiesSingleton {
    private static AppPropertiesSingleton INSTANCE;
    YamlReader yamlReader = new YamlReader();

    private AppPropertiesSingleton() {
        setEnvironmentProperties();
        setBrowserProperties();
        setLoggingProperties();
    }

    private void setLoggingProperties() {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
    }

    private void setEnvironmentProperties() {
        List<EnvironmentModel> envs = yamlReader.getConfig().environment.getAllEnv();
        for (EnvironmentModel env : envs) {
            if (env.getEnvName().equals(getActiveEnv())) {
                for (Map.Entry<String, Object> entry : env.getEnvironmentProperties().entrySet()) {
                    System.setProperty(entry.getKey(), entry.getValue().toString());
                }
            }
        }
    }

    private void setBrowserProperties() {
        for (Map.Entry<String, Object> entry : yamlReader.getConfig().getBrowser().getProperties().entrySet()) {
            System.setProperty(entry.getKey(), entry.getValue().toString());
        }
    }

    public String getActiveEnv() {
        return yamlReader.getConfig().activeEnvironment.getActiveEnvironment();
    }

    public static AppPropertiesSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppPropertiesSingleton();
        }
        return INSTANCE;
    }


}
