package com.ai.openai.achieve.defaults.session;

import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.api.OpenaiApiServer;
import com.ai.openai.achieve.standard.interfaceSession.FilesSession;
import com.ai.openai.endPoint.files.FileObject;
import com.ai.openai.endPoint.files.resp.DeleteFileResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * @Description: OpenAI 文件类会话
 **/
public class DefaultFilesSession implements FilesSession {

    /**
     * 配置信息
     */
    private Configuration configuration;
    /**
     * OpenAI 接口
     */
    private OpenaiApiServer openaiApiServer;

    public DefaultFilesSession(Configuration configuration) {
        this.configuration = configuration;
        this.openaiApiServer = configuration.getOpenaiApiServer();
    }

    @Override
    public List<FileObject> listFilesCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser) {
        return this.openaiApiServer.listFilesCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser).blockingGet().getData();
    }

    @Override
    public FileObject uploadFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, File file, String purpose) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        RequestBody purposeBody = RequestBody.create(MediaType.parse("multipart/form-data"), purpose);
        return this.openaiApiServer.uploadFileCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, multipartBody, purposeBody).blockingGet();
    }

    @Override
    public DeleteFileResponse deleteFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId) {
        return this.openaiApiServer.deleteFileCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fileId).blockingGet();
    }

    @Override
    public FileObject retrieveFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId) {
        return this.openaiApiServer.retrieveFileCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fileId).blockingGet();
    }

    @Override
    public ResponseBody retrieveFileContextCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId) {
        return this.openaiApiServer.retrieveFileContentCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fileId).blockingGet();
    }


}
