package com.WDS.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;

import java.io.InputStream;

public class TxCosUtil {
    /**
     * @param key 云端文件名
     * @param in  输入流
     * @throws Exception 异常信息
     */
    public static String uploadFile(String key, InputStream in) throws Exception {
        String SECRET_ID = System.getenv("SECRET_ID");
        String SECRET_KEY = System.getenv("SECRET_KEY");
        final String REGION_NAME = "cos.ap-shanghai";
        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        final String BUCKET_NAME = "wds-1307612951";
        // 设置用户身份信息。
        // SECRETID 和 SECRETKEY 请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);

        // ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig();

        // 设置 bucket 的地域
        // COS_REGION 请参见 https://cloud.tencent.com/document/product/436/6224
        clientConfig.setRegion(new Region(REGION_NAME));

        // 以下的设置，是可选的：

        // 设置 socket 读取超时，默认 30s
        // clientConfig.setSocketTimeout(30*1000);
        // 设置建立连接超时，默认 30s
        // clientConfig.setConnectionTimeout(30*1000);

        // 如果需要的话，设置 http 代理，ip 以及 port
        // clientConfig.setHttpProxyIp("httpProxyIp");
        // clientConfig.setHttpProxyPort(80);

        // 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
        // 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了

        int contentLength = in.available();
        objectMetadata.setContentLength(contentLength);


        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, in, objectMetadata);


        // 设置存储类型（如有需要，不需要请忽略此行代码）, 默认是标准(Standard), 低频(standard_ia)
        // 更多存储类型请参见 https://cloud.tencent.com/document/product/436/33417
        putObjectRequest.setStorageClass(StorageClass.Standard_IA);

        String url = "https://" + BUCKET_NAME + "." + REGION_NAME + ".myqcloud.com/" + key;
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult.getRequestId());
        } catch (CosClientException e) {
            e.printStackTrace();
        }

        // 确认本进程不再使用 cosClient 实例之后，关闭即可
        cosClient.shutdown();
        return url;
    }
}
