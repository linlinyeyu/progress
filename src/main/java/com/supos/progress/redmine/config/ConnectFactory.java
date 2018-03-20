package com.supos.progress.redmine.config;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectFactory {
    @Value("${redmine.url}")
    private String url;
    @Value("${redmine.apiAccessKey}")
    private String apiAccessKey;

    @Bean
    public RedmineManager redmineManager(){
        RedmineManager manager = RedmineManagerFactory.createWithApiKey(url,apiAccessKey);
        return manager;
    }
}
