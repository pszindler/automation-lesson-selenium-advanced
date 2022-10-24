package Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class YamlReader {
    private static final Logger logger = LoggerFactory.getLogger(YamlReader.class);
    @Getter
    Configuration config;
    File file = new File("src/main/resources/config.yml");

    public YamlReader() {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            config = objectMapper.readValue(file, Configuration.class);
            logger.info("YAML successfully open ({}).", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
