package com.xunjer.linsencommon.utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yuansheng
 * @Title: 文件存储工具
 * @Description:
 * @date 2020/7/1617:23
 */
public class FileSaveUtils {

    public static Boolean byteArraySaveToFile(byte[] bytes,String filePath) throws IOException {
        String path = filePath.substring(0,filePath.lastIndexOf(File.separator));
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        File realFile = new File(filePath);
        if(!realFile.exists()){
            realFile.createNewFile();
        }
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(realFile);
            fos.write(bytes,0,bytes.length);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if(fos!=null){
                fos.close();
            }
            return false;
        }

    }

}
