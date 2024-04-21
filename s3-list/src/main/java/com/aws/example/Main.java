package com.aws.example;

import java.util.List;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class Main {
    public static void main(String[] args) {

        try {

            Region region = Region.US_EAST_1;

            S3Client s3client = S3Client.builder().region(region).build();

            ListBucketsResponse response = s3client.listBuckets();

            List<Bucket> bucketList = response.buckets();

            for (Bucket bucket : bucketList) {
                System.out.println("Bucket name " + bucket.name());
            }

        } catch (S3Exception e) {
            System.out.println("Error with aws authentification");
        } catch (AwsServiceException e) {
            System.out.println("Error with aws authentification");
        } catch (SdkClientException e) {
            System.out.println("Error with aws authentification");
        }

    }
}