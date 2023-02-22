package com.works.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("prod")
public class ProdConfig implements IConfig {

    @Override
    public Map<String, Object> info() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("token", "prod_12k3123hj213h1j2h3");
        hm.put("rowCount", 30);
        hm.put("model", "dark");
        return hm;
    }

}
