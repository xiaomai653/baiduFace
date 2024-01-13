package com.jni.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jni.common.Result;
import com.jni.entity.GroupVo;
import com.jni.entity.UserFace;
import com.jni.face.FaceManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Api(value = "FaceLibController", description = "本地人脸库管理接口")
@RestController
@RequestMapping(value = "/facelib")
public class FaceLibController {

    @Autowired
    private FaceManager faceManager;

    @Value("${upload_path}")
    String upload_path;

    @ApiOperation(value = "获取组列表", notes = "获取组列表")
    @GetMapping(value = "/getGroupList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageStart", value = "起始页码", example = "0", required = true, dataType = "int", paramType = "query", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", example = "10", required = true, dataType = "int", paramType = "query", dataTypeClass = Integer.class)
    })
    public JSONObject getGroupList(@RequestParam(defaultValue = "0") int pageStart, @RequestParam(defaultValue = "100") int pageSize) {
        String groupList = faceManager.getGroupList(pageStart, pageSize);
        JSONObject jsonObject = JSONObject.parseObject(groupList);
        GroupVo groupVo = JSON.parseObject(groupList, GroupVo.class);
        return jsonObject;
    }

    @ApiOperation(value = "添加组", notes = "添加组")
    @GetMapping(value = "/addGroup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "组ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class)
    })
    public JSONObject addGroup(@RequestParam(defaultValue = "groupId") String groupId) {
        return JSONObject.parseObject(faceManager.addGroup(groupId));
    }

    @ApiOperation(value = "删除组", notes = "删除组")
    @GetMapping(value = "/removeGroup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupid", value = "组ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class)
    })
    public JSONObject removeGroup(@RequestParam(defaultValue = "groupId") String groupId) {
        return JSONObject.parseObject(faceManager.removeGroup(groupId));
    }

    @ApiOperation(value = "获取某组的用户列表", notes = "获取某组的用户列表")
    @GetMapping(value = "/getUserList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "组ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageStart", value = "起始页码", example = "0", required = true, dataType = "int", paramType = "query", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", example = "10", required = true, dataType = "int", paramType = "query", dataTypeClass = Integer.class)
    })
    public JSONObject getUserList(@RequestParam(defaultValue = "groupId") String groupId,
                                  @RequestParam(defaultValue = "0") int pageStart,
                                  @RequestParam(defaultValue = "10") int pageSize) {
        return JSONObject.parseObject(faceManager.getUserList(groupId, pageStart, pageSize));
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @GetMapping(value = "/removeUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class),
            @ApiImplicitParam(name = "groupId", value = "组ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class)
    })
    public JSONObject removeUser(
            @RequestParam(defaultValue = "userId") String userId,
            @RequestParam(defaultValue = "groupId") String groupId) throws Exception {
        return JSONObject.parseObject(faceManager.removeUser(userId, groupId));
    }

    @ApiOperation(value = "获取用户详情", notes = "获取用户详情")
    @GetMapping(value = "/getUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class),
            @ApiImplicitParam(name = "groupId", value = "组ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class)
    })
    public JSONObject getUser(
            @RequestParam(defaultValue = "userId") String userId,
            @RequestParam(defaultValue = "groupId") String groupId) throws Exception {
        return JSONObject.parseObject(faceManager.getUser(userId, groupId));
    }

    @ApiOperation(value = "获取人脸数量", notes = "获取人脸数量")
    @GetMapping(value = "/getDbFaceCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "组ID", required = true, dataType = "string", paramType = "query", dataTypeClass = String.class)
    })
    public Result getDbFaceCount(@RequestParam(defaultValue = "groupId") String groupId) throws Exception {
        return Result.success(faceManager.getDbFaceCountByGroup(groupId));
    }

    @ApiOperation(value = "用户入组", notes = "用户入组")
    @PostMapping(value = "/addUser")
    public Result addUser(UserFace face) {
        return faceManager.addUser(face);
    }

    @ApiOperation(value = "用户更新", notes = "用户更新")
    @PostMapping(value = "/updateUser")
    public Result updateUser(UserFace face) {
        return faceManager.updateUser(face);
    }

  /*  @ApiOperation(value = "上传图像", notes = "上传图像")
    @PostMapping(value = "/uploadImage")
    public Result uploadImg(@RequestBody MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail("上传失败，请选择文件");
        }
        try {
            // String fileName =  file.getOriginalFilename();
            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + fileSuffix;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String currentDate = dateFormat.format(new Date());
            String filePath = upload_path + "/" + currentDate + "/";

            filePath += fileName;
            File dest = new File(filePath);
            if (dest.mkdirs()) {
                file.transferTo(dest);
                return Result.success(filePath);
            } else {
                return Result.fail("创建文件夹失败");
            }
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }

    }
*/
    @ApiOperation(value = "识别用户", notes = "识别用户")
    @PostMapping(value = "/identify")
    public Result identify(@RequestParam MultipartFile file) {
        return faceManager.identify(file);
    }

}