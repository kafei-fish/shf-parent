package com.atguigu.uitl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Properties;

/**
 * 文件上传
 */
public class OOSUitil {


    private static String ACCESS_KEY = "oupBEF0-XT50Km7J8BQnNzP7YZKPbV8sdjpiGfq_";
    private static  String SECRET_KEY ="CBpDntd3gZjunrxDLu-VemFAVAtvp6WCDRJYmtyz";
    private static String BUCKET ="myblogdemo";




    //字节流上传
    public static void uploadFile(byte[] bytes,String  fileName){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
////...生成上传凭证，然后准备上传
//        String accessKey = "oupBEF0-XT50Km7J8BQnNzP7YZKPbV8sdjpiGfq_";
//        String secretKey = "CBpDntd3gZjunrxDLu-VemFAVAtvp6WCDRJYmtyz";
//        String bucket = "myblogdemo";

//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;

        byte[] uploadBytes = bytes;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);

        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet);

        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }
    //删除文件
    public static void deleteFileFromQiniu(String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        String key = fileName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(BUCKET, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
