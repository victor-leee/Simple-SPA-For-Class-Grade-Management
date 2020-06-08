package cn.leetechweb.pj4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.config
 * Time : 2020/6/3 23:43
 *
 * @author : Victor
 */
@Configuration
@PropertySources(
        @PropertySource(value = "classpath:config.properties")
)
public class SpringGlobalConfig {
}
