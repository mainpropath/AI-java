package com.ai.openAi.endPoint.files.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteFileResponse implements Serializable {

    /**
     * 文件ID
     */
    private String id;
    private String object;
    private Boolean deleted;

}
