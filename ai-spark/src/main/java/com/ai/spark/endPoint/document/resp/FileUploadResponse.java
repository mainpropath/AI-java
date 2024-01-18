package com.ai.spark.endPoint.document.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileUploadResponse {
    /**
     * 返回上传的 fileId
     */
    private Integer code;
    /**
     * 请求唯一 id，用于问题定位
     */
    private String sid;
    /**
     * 结果描述
     */
    private String desc;
    /**
     * 返回结果
     */
    private com.ai.spark.endPoint.document.Data data;
}
