package com.ai.openai.common;

import com.ai.openai.common.exception.IError;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 通用类
 */
public class Constants {

    public static final String NULL = "NULL";
    public static final String API_KEY = "apiKey";
    public static final String API_HOST = "apiHost";
    public static final String URL = "url";
    public static final Map<Integer, String> ERROR_MSG_MAP = new HashMap<>();

    static {
        ERROR_MSG_MAP.put(ErrorMsg.OPENAI_AUTHENTICATION_ERROR.code(), ErrorMsg.OPENAI_AUTHENTICATION_ERROR.msg());
        ERROR_MSG_MAP.put(ErrorMsg.OPENAI_LIMIT_ERROR.code(), ErrorMsg.OPENAI_LIMIT_ERROR.msg());
        ERROR_MSG_MAP.put(ErrorMsg.OPENAI_SERVER_ERROR.code(), ErrorMsg.OPENAI_SERVER_ERROR.msg());
    }

    public enum ErrorMsg implements IError {
        MESSAGE_NOT_NUL(500, "Message 不能为空"),
        API_KEYS_NOT_NUL(500, "API KEYS 不能为空"),
        NO_ACTIVE_API_KEYS(500, "没有可用的API KEYS"),
        SYS_ERROR(500, "系统繁忙"),
        PARAM_ERROR(501, "参数异常"),
        RETRY_ERROR(502, "请求异常，请重试~"),
        OPENAI_AUTHENTICATION_ERROR(401, "身份验证无效/提供的 API 密钥不正确/您必须是组织的成员才能使用 API"),
        OPENAI_LIMIT_ERROR(429, "达到请求的速率限制/您超出了当前配额，请检查您的计划和帐单详细信息/发动机当前过载，请稍后重试"),
        OPENAI_SERVER_ERROR(500, "服务器在处理您的请求时出错"),
        ;
        private int code;
        private String msg;

        ErrorMsg(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String msg() {
            return this.msg;
        }

        public int code() {
            return this.code;
        }
    }


    /**
     * 官网支持的请求角色类型为：system、user、assistant
     * system：用来设置 assistant 的行为
     * user：用于指导 assistant
     * assistant：消息帮助存储更早的响应
     */
    @Getter
    @AllArgsConstructor
    public enum Role {

        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String RoleName;
    }

    /**
     * 各接口API路径
     */
    @Getter
    @AllArgsConstructor
    public enum ApiUrl {

        v1_completions("/v1/completions"),
        v1_chat_completions("/v1/chat/completions"),
        v1_audio_speech("/v1/audio/speech"),
        v1_audio_transcriptions("/v1/audio/transcriptions"),
        v1_audio_translations("/v1/audio/translations"),
        v1_embeddings("/v1/embeddings"),
        v1_fine_tuning_jobs("/v1/fine_tuning/jobs"),
        v1_fine_tuning_jobs_fine_tuning_job_id("/v1/fine_tuning/jobs/{fine_tuning_job_id}"),
        v1_fine_tuning_jobs_fine_tuning_job_id_cancel("/v1/fine_tuning/jobs/{fine_tuning_job_id}/cancel"),
        v1_fine_tuning_jobs_fine_tuning_job_id_events("/v1/fine_tuning/jobs/{fine_tuning_job_id}/events"),
        ;

        private String code;
    }

}