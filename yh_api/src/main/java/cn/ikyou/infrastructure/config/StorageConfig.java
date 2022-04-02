package cn.ikyou.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ouyangrongtao
 * @version 1.0
 * @date 2021/1/4 11:46
 **/
@Component
@ConfigurationProperties(prefix = "storage")
public class StorageConfig {

    private StorageEndPoint minio;

    private StorageEndPoint alioss;

    public StorageEndPoint getMinio() {
        return minio;
    }

    public void setMinio(StorageEndPoint minio) {
        this.minio = minio;
    }

    public StorageEndPoint getAlioss() {
        return alioss;
    }

    public void setAlioss(StorageEndPoint alioss) {
        this.alioss = alioss;
    }

}
