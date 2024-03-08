package com.ai.openai.achieve.defaults.session;

import com.ai.openai.achieve.Configuration;
import com.ai.openai.achieve.standard.session.FilesSession;
import com.ai.openai.endPoint.files.FileObject;
import com.ai.openai.endPoint.files.resp.DeleteFileResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.File;
import java.util.List;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * @Description: OpenAI 文件类会话
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DefaultFilesSession extends Session implements FilesSession {

    public DefaultFilesSession(Configuration configuration) {
        this.setConfiguration(ensureNotNull(configuration, "configuration"));
        this.setOpenaiApiServer(ensureNotNull(configuration.getOpenaiApiServer(), "openaiApiServer"));
    }

    @Override
    public List<FileObject> listFilesCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser) {
        return this.getOpenaiApiServer().listFilesCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser).blockingGet().getData();
    }

    @Override
    public FileObject uploadFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, File file, String purpose) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), fileBody);
        RequestBody purposeBody = RequestBody.create(MediaType.parse("multipart/form-data"), purpose);
        return this.getOpenaiApiServer().uploadFileCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, multipartBody, purposeBody).blockingGet();
    }

    @Override
    public DeleteFileResponse deleteFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId) {
        return this.getOpenaiApiServer().deleteFileCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fileId).blockingGet();
    }

    @Override
    public FileObject retrieveFileCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId) {
        return this.getOpenaiApiServer().retrieveFileCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fileId).blockingGet();
    }

    @Override
    public ResponseBody retrieveFileContextCompletions(String apiHostByUser, String apiKeyByUser, String apiUrlByUser, String fileId) {
        return this.getOpenaiApiServer().retrieveFileContentCompletion(apiHostByUser, apiKeyByUser, apiUrlByUser, fileId).blockingGet();
    }


}
