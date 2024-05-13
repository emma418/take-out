package com.piano.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "piano.aws")
@Data
public class S3Properties {
    private String region;
    private String accessKey;
    private String secretAccessKey;
    private String bucketName;
}
