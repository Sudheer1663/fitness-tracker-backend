//package fitness.service;
//
//import io.minio.*;
//import io.minio.errors.*;
//import io.minio.http.Method;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class MinioService {
//
//    private static final Logger logger = LoggerFactory.getLogger(MinioService.class);
//
//    private final MinioClient minioClient;
//
//    @Value("${minio.bucket.name:fitness-bucket}")
//    private String bucketName;
//
//    @Value("${minio.url}")
//    private String minioUrl;
//
//    public MinioService(MinioClient minioClient) {
//        this.minioClient = minioClient;
//    }
//
//    public String uploadFile(MultipartFile file) {
//        try {
//            // Validate file
//            if (file.isEmpty()) {
//                throw new IllegalArgumentException("File is empty");
//            }
//
//            // Ensure bucket exists
//            createBucketIfNotExists();
//
//            // Generate unique filename
//            String originalFilename = file.getOriginalFilename();
//            String fileExtension = getFileExtension(originalFilename);
//            String fileName = UUID.randomUUID().toString() + fileExtension;
//
//            logger.info("Uploading file: {} as {}", originalFilename, fileName);
//
//            // Upload file to MinIO
//            try (InputStream inputStream = file.getInputStream()) {
//                minioClient.putObject(
//                    PutObjectArgs.builder()
//                        .bucket(bucketName)
//                        .object(fileName)
//                        .stream(inputStream, file.getSize(), -1)
//                        .contentType(file.getContentType())
//                        .build()
//                );
//            }
//
//           
//            String fileUrl = getFileUrl(fileName);
//            logger.info("File uploaded successfully: {}", fileUrl);
//            
//            return fileUrl;
//
//        } catch (Exception e) {
//            logger.error("Failed to upload file to MinIO: {}", e.getMessage());
//            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
//        }
//    }
//
//    public void deleteFile(String fileName) {
//        try {
//            minioClient.removeObject(
//                RemoveObjectArgs.builder()
//                    .bucket(bucketName)
//                    .object(extractFileNameFromUrl(fileName))
//                    .build()
//            );
//            logger.info("File deleted successfully: {}", fileName);
//        } catch (Exception e) {
//            logger.error("Failed to delete file from MinIO: {}", e.getMessage());
//            throw new RuntimeException("File deletion failed: " + e.getMessage(), e);
//        }
//    }
//
//    public boolean fileExists(String fileName) {
//        try {
//            minioClient.statObject(
//                StatObjectArgs.builder()
//                    .bucket(bucketName)
//                    .object(extractFileNameFromUrl(fileName))
//                    .build()
//            );
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private void createBucketIfNotExists() throws Exception {
//        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
//        if (!bucketExists) {
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
//            
//            // Set bucket policy to public read (optional)
//            String policy = """
//                {
//                    "Version": "2012-10-17",
//                    "Statement": [
//                        {
//                            "Effect": "Allow",
//                            "Principal": "*",
//                            "Action": ["s3:GetObject"],
//                            "Resource": ["arn:aws:s3:::%s/*"]
//                        }
//                    ]
//                }
//                """.formatted(bucketName);
//                
//            minioClient.setBucketPolicy(
//                SetBucketPolicyArgs.builder()
//                    .bucket(bucketName)
//                    .config(policy)
//                    .build()
//            );
//            logger.info("Bucket created: {}", bucketName);
//        }
//    }
//
//    private String getFileUrl(String fileName) throws Exception {
//        return minioClient.getPresignedObjectUrl(
//            GetPresignedObjectUrlArgs.builder()
//                .bucket(bucketName)
//                .object(fileName)
//                .expiry(7, TimeUnit.DAYS) // URL expires in 7 days
//                .method(Method.GET)
//                .build()
//        );
//    }
//
//    private String getFileExtension(String filename) {
//        if (filename == null || filename.lastIndexOf(".") == -1) {
//            return "";
//        }
//        return filename.substring(filename.lastIndexOf("."));
//    }
//
//    private String extractFileNameFromUrl(String url) {
//        if (url == null || url.isEmpty()) {
//            return "";
//        }
//        // Extract filename from URL (last part after last slash)
//        return url.substring(url.lastIndexOf("/") + 1);
//    }
//}