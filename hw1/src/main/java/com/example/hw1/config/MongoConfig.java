package com.example.hw1.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.hw1.dao")
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${mongo.db.name}")
    private String mongoDbName;

    @Value("${mongo.db.host}")
    private String mongoUri;

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(mongoUri);
    }

}