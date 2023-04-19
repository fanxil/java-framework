package configcenter.controller;

import configcenter.store.Config;
import configcenter.store.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigDao configDao;

    @GetMapping("/{key}")
    public Config getConfig(@PathVariable String key) {
        Config config = configDao.findByKey(key);
        return config;
    }

    @PutMapping("/{key}")
    public void updateConfig(@PathVariable String key, @RequestBody Config config) {
        config.setKey(key);
        configDao.save(config);
    }

    @DeleteMapping("/{key}")
    public void deleteConfig(@PathVariable String key) {
        configDao.deleteByKey(key);
    }
}
