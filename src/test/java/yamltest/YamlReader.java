package yamltest;

/**
 * @Author wenbaoxie
 * @Date 2020/9/29
 */
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class YamlReader {

    private static Map<String, Map<String, Object>> properties;

    private YamlReader() {
        if (SingletonHolder.instance != null) {
            throw new IllegalStateException();
        }
    }

    /**
     * use static inner class  achieve singleton
     */
    private static class SingletonHolder {
        private static YamlReader instance = new YamlReader();
    }

    public static YamlReader getInstance() {
        return SingletonHolder.instance;
    }

    //init property when class is loaded
    static {

        InputStream in = null;
        try {
            properties = new HashMap<>();
            Yaml yaml = new Yaml();
            in = YamlReader.class.getClassLoader().getResourceAsStream("application.yaml");
            properties = yaml.loadAs(in, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get yaml property
     *
     * @param key
     * @return
     */
    public Object getValueByKey(String root, String key) {
        Map<String, Object> rootProperty = properties.get(root);
        return rootProperty.getOrDefault(key, "");
    }
}

