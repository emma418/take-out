package com.piano.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "piano.jwt")
@Data
public class JwtProperties {

    /**
     * Configuration related to generate jwt token for admin
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * Configuration related to generate jwt token for users
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
}
