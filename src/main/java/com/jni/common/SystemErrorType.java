package com.jni.common;

import lombok.Getter;

/**
 * 系统级别3个0
 */
@Getter
public enum SystemErrorType implements ErrorType {

    // 错误码
    SYSTEM_ERROR("-1", "系统异常"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),
    UNKNOWN_ERROR("000400", "未知错误"),

    GATEWAY_NOT_FOUND_SERVICE("100404", "服务未找到"),
    GATEWAY_ERROR("100500", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("100002", "网关超时"),

    INVALID_TOKEN("200001", "无效token"),
    NO_PERMISSION("200200", "没有权限"),
    ACCESS_TOKEN_EXPIRE("200300", "accessToken过期"),
    REFRESH_TOKEN_EXPIRE("200400", "refreshToken过期，请重新登录"),
    NO_REFRESH_TOKEN("2004002", "非法refreshToken,请重新登录"),
    NO_ADMIN_ROLE("200500", "没有管理员角色"),
    PASSWORD_ERROR("200601", "密码错误"),
    USERNAME_ERROR("200600", "账号错误"),
    NO_DATA("200800", "暂无数据"),
    USERNAME_PASSWORD_EMPTY("2006002", "账号密码不能为空"),
    AUTHORIZATION_EMPTY("2006003", "Authorization不能为空"),
    AUTHORIZATION_ERROR("2006004", "Authorization错误"),
    ARGUMENT_NOT_VALID("200700", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("200900", "上传文件大小超过限制"),

    /**
     * 百度SDK
     */
    SUCCESS("0", "成功"),
    ILLEGAL_PARAMS("-1", "失败或非法参数"),
    MEMORY_ALLOCATION_FAILED("-2", "内存分配失败"),
    INSTANCE_IS_EMPTY("-3", "实例对象为空"),
    MODEL_IS_EMPTY("-4", "模型内容为空"),
    UNSUPPORT_ABILITY_TYPE("-5", "不支持的能力类型"),
    UNSUPPORT_INFER_TYPE("-6", "不支持的预测库类型"),
    NN_CREATE_FAILED("-7", "预测库对象创建失败"),
    NN_INIT_FAILED("-8", "预测库对象初始化失败"),
    IMAGE_IS_EMPTY("-9", "图像数据为空"),
    ABILITY_INIT_FAILED("-10", "人脸能力初始化失败"),
    ABILITY_UNLOAD("-11", "人脸能力未加载"),
    ABILITY_ALREADY_LOADED("-12", "人脸能力已加载"),
    NOT_AUTHORIZED("-13", "未授权"),
    ABILITY_RUN_EXCEPTION("-14", "人脸能力运行异常"),
    UNSUPPORT_IMAGE_TYPE("-15", "不支持的图像类型"),
    IMAGE_TRANSFORM_FAILED("-16", "图像转换失败"),
    SYSTEM_ERROR1("-1001", "系统错误"),
    PARARM_ERROR("-1002", "参数错误"),
    DB_OP_FAILED("-1003", "数据库操作失败"),
    NO_DATA1("-1004", "没有数据"),
    RECORD_UNEXIST("-1005", "记录不存在"),
    RECORD_ALREADY_EXIST("-1006", "记录已经存在"),
    FILE_NOT_EXIST("-1007", "文件不存在"),
    GET_FEATURE_FAIL("-1008", "提取特征值失败"),
    FILE_TOO_BIG("-1009", "文件太大"),
    FACE_RESOURCE_NOT_EXIST("-1010", "人脸资源文件不存在"),
    FEATURE_LEN_ERROR("-1011", "特征值长度错误"),
    DETECT_NO_FACE("-1012", "未检测到人脸"),
    CAMERA_ERROR("-1013", "摄像头错误或不存在"),
    FACE_INSTANCE_ERROR("-1014", "人脸引擎初始化错误"),
    LICENSE_FILE_NOT_EXIST("-1015", "授权文件不存在"),
    LICENSE_KEY_EMPTY("-1016", "授权序列号为空"),
    LICENSE_KEY_INVALID("-1017", "授权序列号无效"),
    LICENSE_KEY_EXPIRE("-1018", "授权序序列号过期"),
    LICENSE_ALREADY_USED("-1019", "授权序列号已被使用"),
    DEVICE_ID_EMPTY("-1020", "设备指纹为空"),
    NETWORK_TIMEOUT("-1021", "网络超时"),
    NETWORK_ERROR("-1022", "网络错误");


    /**
     * 错误码
     */
    private String code;

    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(String code) {
        for (SystemErrorType systemErrorType : values()) {
            if (systemErrorType.code.equals(code)) {
                return systemErrorType.msg;
            }
        }
        return "Code not found";
    }

}
