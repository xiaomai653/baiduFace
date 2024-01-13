package com.jni.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@ApiModel(value = "UserFace", description = "人脸库对象")
@Data
public class UserFace {

    // 人脸图像
    private String image;
    // 用户id
    private String userId;
    // 组id
    private String groupId;
    // 其它信息
    private String userInfo;

    private MultipartFile imageFile;


}
