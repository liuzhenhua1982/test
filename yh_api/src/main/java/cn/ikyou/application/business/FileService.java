package cn.ikyou.application.business;

import org.springframework.web.multipart.MultipartFile;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
public interface FileService {

    String upload(MultipartFile file);

    byte[] download(String fileName);
}
