package org.ht.id.externalcrm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "org.ht.id.externalcrm.repository")
public class AppConfig {
}
