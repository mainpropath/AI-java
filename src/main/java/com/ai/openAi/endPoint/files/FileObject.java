package com.ai.openAi.endPoint.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileObject implements Serializable {

    /**
     * 文件标识符，可在 API 端点中引用
     */
    private String id;

    /**
     * 文件大小（以字节为单位）
     */
    private Long bytes;

    /**
     * 创建文件时的 Unix 时间戳（以秒为单位）
     */
    @JsonProperty("created_at")
    private Long createdAt;

    /**
     * 文件的名称
     */
    private String filename;

    /**
     * 对象类型，始终为:file
     */
    private String object;

    /**
     * 文件的预期用途
     */
    private String purpose;

    /**
     * 荒废的。文件的当前状态
     */
    @Deprecated
    private String status;

    /**
     * 荒废的。有关微调训练文件验证失败的原因的详细信息
     */
    @Deprecated
    @JsonProperty("status_details")
    private String statusDetails;
}
