package com.zhuoyueben.gmail.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-12 20:44
 */
public class UploadImageUtil {
    private String imgUrl="http://192.168.153.141";

    public String uploadImage(MultipartFile multipartFile) throws IOException, MyException {
        String file = this.getClass().getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer=trackerClient.getTrackerServer();
        StorageClient storageClient=new StorageClient(trackerServer,null);
        byte[] bytes = multipartFile.getBytes();
        String originalFilename = multipartFile.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String s = originalFilename.substring(i + 1);
        String imgurl=imgUrl;
        String[] upload_file = storageClient.upload_file(bytes, s, null);
        for(String uploadInfo:upload_file){
            imgurl+="/"+uploadInfo;
        }
        return imgurl;
    }
}
