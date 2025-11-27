package com.example.demo.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic employeeCreatedTopic() {
        return TopicBuilder.name("employee.created").build();
    }

    @Bean
    public NewTopic employeeUpdatedTopic() {
        return TopicBuilder.name("employee.updated").build();
    }
}