package com.github.tonydeng.tsc.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URL;

/**
 * Created by tonydeng on 16/1/25.
 */
public class LogbackConfigurer {
    private static final Logger log = LoggerFactory.getLogger(LogbackConfigurer.class);

    /**
     * 初始化日志配置
     *
     * @param logback
     */
    public void contextInitialized(String logback) {
        try {
            URL logURL = new ClassPathResource(logback).getURL();
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            context.reset();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            configurator.doConfigure(logURL);
            if (log.isInfoEnabled())
                log.info("loaded logback configure sucess!");
        } catch (IOException e) {
            log.error("load logback config fail !" + e.toString());
        } catch (JoranException e) {
            log.error("load logback config fail !" + e.toString());
        }

    }
}