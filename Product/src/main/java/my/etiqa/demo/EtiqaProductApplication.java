package my.etiqa.demo;

import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableCaching
@EnableWebFlux
@OpenAPIDefinition(info = @Info(
        title = "Etiqa Hackaton Test ",
        version = "1.0",
        description = "Etiqa Hackaton Test API Document"
))
@SpringBootApplication //(exclude = {org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration.class})
public class EtiqaProductApplication {


    public static void main(String[] args) {
        SpringApplication.run(EtiqaProductApplication.class, args);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    /*@Bean(name = "uniqueHazelcastConfig")
    public Config hazelcastConfig() {
        return new Config().addMapConfig(new MapConfig()
                .setName("products")
                .setTimeToLiveSeconds(3600)
                .setBackupCount(1));
    }*/


}
