package com.piano.utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

@Slf4j
@Data
@AllArgsConstructor
public class S3Util {

    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * file upload
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .build();

        try {
            // Creating a PutObject request
            PutObjectRequest request = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes), null);
            s3Client.putObject(request);
        } catch (AmazonServiceException ase) {
            log.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            log.error("Error Message:    " + ase.getMessage());
            log.error("HTTP Status Code: " + ase.getStatusCode());
            log.error("AWS Error Code:   " + ase.getErrorCode());
            log.error("Error Type:       " + ase.getErrorType());
            log.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ace.getMessage());
        } finally {
            if (s3Client != null) {
                s3Client.shutdown();
            }
        }

        // Construct the URL to access the uploaded object
        String url = s3Client.getUrl(bucketName, objectName).toString();
        log.info("File uploaded to: {}", url);
        return url;

    }

}
