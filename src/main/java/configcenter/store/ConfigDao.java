package configcenter.store;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConfigDao {

    public Config findByKey(String key) {

        return new Config("key", "value");
    }

    public Config save(Config config) {
        return new Config("key", "value");
    }

    public List<String> findAllKeys() {
        return Lists.newArrayList("key");
    }

    public void deleteByKey(String key) {
    }
}