package com.burton.lts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Tainy
 * @date 2018/6/21 19:06
 */
@Configuration
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource(locations = {"classpath:dubbo/*.xml"})
public class DubboConfig {

}
