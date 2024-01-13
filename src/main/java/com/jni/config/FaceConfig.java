package com.jni.config;

import com.jni.common.SystemErrorType;
import com.jni.face.Face;
import com.jni.face.FaceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FaceConfig {

    @Bean
    public Face config() {
        /*  sdk初始化 */
        Face api = new Face();
        // model_path为模型文件夹路径，即models文件夹（里面存的是人脸识别的模型文件）
        // 传空为采用默认路径，若想定置化路径，请填写全局路径如：d:\\face （models模型文件夹目录放置后为d:\\face\\models）
        // 若模型文件夹采用定置化路径，则激活文件(license.ini, license.key)也可采用定制化路径放置到该目录如d:\\face\\license
        // 亦可在激活文件默认生成的路径
        String modelPath = "";
        int res = api.sdkInit(modelPath);
        if (res != 0) {
            log.error("sdk初始化失败：{}", SystemErrorType.getMsgByCode(String.valueOf(res)));
        }
        return api;
    }

    @Bean
    public FaceManager manager() {
        return new FaceManager();
    }
}
