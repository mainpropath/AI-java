package com.ai.openai.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

    private String url;
}
