package com.leyou.upload.service.serviceimpl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LeyouException;
import com.leyou.config.UploadProperties;
import com.leyou.upload.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

//    @Autowired
//    private UploadProperties properties;

    private static final Logger logger= LoggerFactory.getLogger(UploadServiceImpl.class);

    /**
     *     支持上传的文件类型
     */
    private static final List<String> ALLOW_TYPES = Arrays.asList("image/png","image/jpeg","image/jpg");

    @Override
    public String upload(MultipartFile file) {
        /**
         * 1.图片信息校验
         *      1)校验文件类型
         *      2)校验图片内容
         * 2.保存图片
         *      1)生成保存目录
         *      2)保存图片
         *      3)拼接图片地址
         */
        try {
            String type = file.getContentType();
            if (!ALLOW_TYPES.contains(type)) {
//            if (!properties.getAllowTypes().contains(type)) {
                logger.info("上传文件失败，文件类型不匹配：{}", type);
                throw new LeyouException(ExceptionEnums.INVALID_FILE_TYPE);
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                logger.info("上传失败，文件内容不符合要求");
                throw new LeyouException(ExceptionEnums.INVALID_FILE_TYPE);
            }

//            File dest = new File("D:\\BOS",file.getOriginalFilename());
//            file.transferTo(dest);
//            return "http://image.leyou.com/" + file.getOriginalFilename();

            StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                  file.getInputStream(), file.getSize(), getExtension(file.getOriginalFilename()), null);
            String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
            String url = "http://image.leyou.com/"+path;

//            String url = properties.getBaseUrl()+storePath.getFullPath();
            return url;
        }catch (Exception e){
            logger.error("上传失败");
            throw new LeyouException(ExceptionEnums.UPLOAD_FILE_ERROR);
        }
    }

    public String getExtension(String fileName){
        return StringUtils.substringAfterLast(fileName,".");
    }
}
