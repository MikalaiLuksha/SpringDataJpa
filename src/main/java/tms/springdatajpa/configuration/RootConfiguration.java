package tms.springdatajpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class RootConfiguration {

    @Bean
    public List<UUID> uuidList(){
        return new ArrayList<>();
    }
}
