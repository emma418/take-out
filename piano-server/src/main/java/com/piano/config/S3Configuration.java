package com.piano.config;

import com.piano.properties.S3Properties;
import com.piano.utils.S3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class S3Configuration {

    @Bean
    @ConditionalOnMissingBean
    public S3Util getS3Util(S3Properties s3Properties ) {
        log.info("getS3Util: {}", s3Properties);
        return new S3Util(s3Properties.getRegion(),
                s3Properties.getAccessKey(),
                s3Properties.getSecretAccessKey(),
                s3Properties.getBucketName());
    }
}
