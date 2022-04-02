package cn.ikyou.infrastructure.config;

import cn.ikyou.infrastructure.execption.ServiceCheckException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageClientConfig {

    private final StorageConfig storageConfig;

    @Autowired
    public StorageClientConfig(StorageConfig storageConfig) {
        this.storageConfig = storageConfig;
    }

    @Bean("minioClient")
    @ConditionalOnProperty(name = "storage.minio.endpoint")
    public MinioClient getMinioClient() {
        try {
            return MinioClient.builder().endpoint(storageConfig.getMinio().getEndPoint())
                    .credentials(storageConfig.getMinio().getAccesskey(), storageConfig.getMinio().getSecretkey())
                    .build();
        } catch (Exception e) {
            throw new ServiceCheckException("minio connect error");
        }
    }

    @Bean("aliOSSClient")
    @ConditionalOnProperty(name = "storage.alioss.endpoint")
    public OSS getAliOSSClient() {
        return new OSSClientBuilder().build(storageConfig.getAlioss().getEndPoint(), storageConfig.getAlioss().getAccesskey(), storageConfig.getAlioss().getSecretkey());
    }

}
