package com.cosmos.fax.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class StorageService {

	@Autowired
	private AmazonS3 amazonS3;

	public String upload(String bucketName, String fileName, File file) {
		System.out.println("Inside Storage service upload");
		try {
			amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file));
			return "SUCCESS";
		} catch (AmazonServiceException e) {
			System.out.println("Failed to upload the file::" + e);
			return "FAILED";
		}
	}

	public byte[] download(String path, String key) {
		try {
			S3Object object = amazonS3.getObject(path, key);
			S3ObjectInputStream objectContent = object.getObjectContent();
			return IOUtils.toByteArray(objectContent);
		} catch (AmazonServiceException | IOException e) {
			throw new IllegalStateException("Failed to download the file", e);
		}
	}

}
