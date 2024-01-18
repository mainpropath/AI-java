package com.ai.spark.endPoint.document.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileUploadRequest {

    /**
     * 要上传的文件
     */
    private File file;
    /**
     * 文件 url （文件和文件 url 必须有一个）
     */
    private String url;
    /**
     * 文件名称，带后缀。文件用 url 的方式，该字段必传；传 file 的话，该字段可不传
     */
    private String fileName;
    /**
     * 文件类型，目前传固定值"wiki"
     */
    @Builder.Default
    private String fileType = FileType.wiki.getType();
    /**
     * 文件状态回调地址，文件状态有变动时服务会调用该 url。调用的时候会带上鉴权头，鉴权方式同【接口鉴权】，业务可根据需要是否做鉴权校验
     */
    private String callbackUrl;

    @Getter
    @AllArgsConstructor
    public enum FileType {
        wiki("wiki");
        private String type;
    }

}
