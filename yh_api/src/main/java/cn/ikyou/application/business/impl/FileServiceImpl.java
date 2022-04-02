package cn.ikyou.application.business.impl;

import cn.ikyou.application.business.FileService;
import cn.ikyou.domain.business.entity.TFiles;
import cn.ikyou.domain.business.service.TFilesDomainService;
import cn.ikyou.infrastructure.config.StorageConfig;
import cn.ikyou.infrastructure.util.UserInfoUtil;
import com.aliyun.oss.ServiceException;
import io.minio.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * 开发人：石聪辉
 * 开发时间：2022/1/20
 * 说明：
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final String DEFAULT_BUCKET="default";
    private final ApplicationContext applicationContext;
    private final StorageConfig storageConfig;
    private final TFilesDomainService filesDomainService;


    @Override
    public String upload(MultipartFile file) {
        String orgiginalFileName=file.getOriginalFilename();
        String fileType = orgiginalFileName.substring(orgiginalFileName.lastIndexOf("."));
        String fileName= UUID.randomUUID().toString().replaceAll("-","")+fileType;

        if(null!=storageConfig.getMinio()){
            MinioClient minioClient=applicationContext.getBean(MinioClient.class);
            try{
                BucketExistsArgs bucketExistsArgs=BucketExistsArgs.builder().bucket(DEFAULT_BUCKET).build();
                boolean bl=minioClient.bucketExists(bucketExistsArgs);
                if(!bl){
                    MakeBucketArgs makeBucketArgs=MakeBucketArgs.builder().bucket(DEFAULT_BUCKET).build();
                    minioClient.makeBucket(makeBucketArgs);
                }
                PutObjectArgs putObjectArgs=PutObjectArgs.builder()
                        .object(fileName)
                        .bucket(DEFAULT_BUCKET)
                        .stream(file.getInputStream(),file.getSize(),-1L)
                        .build();
                minioClient.putObject(putObjectArgs);
                //保存文件
                TFiles files=new TFiles();
                files.setCreateId(UserInfoUtil.getCurrentUser().getUserId());
                files.setCreateTime(new Date());
                files.setFileFlag("");
                files.setFileName(fileName);
                files.setFileType(fileType);
                files.setOldName(orgiginalFileName);
                if(!filesDomainService.save(files)){
                    throw new ServiceException("保存文件失败");
                }
                return fileName;
            }catch (Exception e){
                log.error(ExceptionUtils.getMessage(e));
                throw new ServiceException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public byte[] download(String fileName) {
        if(null!=storageConfig.getMinio()){
            MinioClient minioClient=applicationContext.getBean(MinioClient.class);
            try{
                StatObjectArgs statObjectArgs=StatObjectArgs.builder().bucket(DEFAULT_BUCKET)
                        .object(fileName).build();
                StatObjectResponse statObjectResponse=minioClient.statObject(statObjectArgs);
                if(null==statObjectResponse){
                    throw new ServiceException("未发现文件");
                }
                GetObjectArgs getObjectArgs=GetObjectArgs.builder().bucket(DEFAULT_BUCKET)
                        .object(fileName).build();
                GetObjectResponse objectResponse=minioClient.getObject(getObjectArgs);
                byte[] buf=new byte[1024];
                int len;
                try(FastByteArrayOutputStream os =new FastByteArrayOutputStream()){
                    while ((len=objectResponse.read(buf))!=-1){
                        os.write(buf,0,len);
                    }
                    os.flush();
                    return os.toByteArray();
                }
            }catch (Exception e){
                log.error(ExceptionUtils.getMessage(e));
                throw new ServiceException(e.getMessage());
            }
        }
        return null;
    }


}
