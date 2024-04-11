package com.example.show_bean.a04;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "java")
public class Bean4 {
    private String home;
    private String version;
}

