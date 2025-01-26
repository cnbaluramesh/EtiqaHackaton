/*
package my.etiqa.demo.config;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        instance.getConfig().addMapConfig(new MapConfig("stats").setTimeToLiveSeconds(5));
        return instance;
    }

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig cfg = ClientConfig.load();
        cfg.setClusterName("statsCluster");
        return cfg;
    }

}*/
