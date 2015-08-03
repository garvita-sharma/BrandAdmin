package com.sd.ZendeskImpl;

import java.io.ByteArrayInputStream;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
*/
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.promocategory.service.IStorageService;

public class StorageServiceImpl {

	/*@Autowired
	private Environment env;
*/
	private AmazonS3 s3client = null;

	private static final String cacheControl = "max-age=31536000";

	private AmazonS3 getS3Client() {
		/*String accessKey = env.getProperty("s3.accesskey");
		String secretKey = env.getProperty("s3.secretkey");*/
		
		String accessKey = "";
		String secretKey = "";
		AWSCredentials s3Credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		s3client = new AmazonS3Client(s3Credentials);
		return s3client;
	}

	public String uploadFile(String key, byte[] bs, String contentType)
			throws AmazonServiceException, AmazonClientException {
		try {
			//String bucketName = env.getProperty("s3.bucketname");
			String bucketName = "";

			AmazonS3 s3Client = getS3Client();
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			Long contentLength = Long.valueOf(bs.length);
			metadata.setContentLength(contentLength);
			metadata.setCacheControl(cacheControl);
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, new ByteArrayInputStream(bs), metadata);
			String md5 = s3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead)).getETag();
			System.out.println(md5);
			return md5;
		} catch (AmazonServiceException ase) {
			System.out.println("Error Message: " + ase.getMessage()
					+ " HTTP Status Code: " + ase.getStatusCode()
					+ " AWS Error Code: " + ase.getErrorCode()
					+ " Error Type: " + ase.getErrorType() + " Request ID: "
					+ ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Error Message: " + ace.getMessage());
		}
		return "";
	}

}
