package com.tancheon.account.config;

import com.tancheon.account.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private final ApplicationProperties applicationProperties;

    @Bean(name = "threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(applicationProperties.getAsyncExecutor().get("core-pool-size"));
        taskExecutor.setMaxPoolSize(applicationProperties.getAsyncExecutor().get("max-pool-size"));
        taskExecutor.setQueueCapacity(applicationProperties.getAsyncExecutor().get("queue-capacity"));
        taskExecutor.setThreadNamePrefix("Async-Executor-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) ->
                log.error("Async failed. method name: {}, exception message: {}", method.getName(), ex.getMessage());
    }
}
