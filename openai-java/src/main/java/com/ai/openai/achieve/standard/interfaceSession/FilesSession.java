package com.ai.openai.achieve.standard.interfaceSession;

import com.ai.openai.endPoint.files.FileObject;
import com.ai.openai.endPoint.files.resp.DeleteFileResponse;
import okhttp3.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * @Description: 文件会话窗口
 **/
public interface FilesSession {
    /**
     * 返回属于用户组织的文件列表
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @return 文件列表
     */
    List<FileObject> listFilesCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser);

    /**
     * 上传文件
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param file          需要上传的文件
     * @param purpose       上传文件的用途
     * @return 文件信息
     */
    FileObject uploadFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, File file, String purpose);

    /**
     * 删除文件
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param fileId        文件ID
     * @return 删除状态信息
     */
    DeleteFileResponse deleteFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId);

    /**
     * 检索文件
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param fileId        文件ID
     * @return 文件信息
     */
    FileObject retrieveFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId);

    /**
     * 获取文件内容
     *
     * @param apiHostByUser 用户自定义 host
     * @param apiKeyByUser  用户自定义密钥
     * @param apiUrlByUser  用户自定义请求地址
     * @param fileId        文件ID
     * @return 返回结果
     */
    ResponseBody retrieveFileContextCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId);
}
