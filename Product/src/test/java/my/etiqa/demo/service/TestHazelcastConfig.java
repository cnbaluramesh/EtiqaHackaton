package my.etiqa.demo.service;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestHazelcastConfig {

    @Bean
    public Config hazelcastConfig() {
        return new Config().addMapConfig(new MapConfig()
                .setName("test-products")
                .setTimeToLiveSeconds(1800));
    }
}
