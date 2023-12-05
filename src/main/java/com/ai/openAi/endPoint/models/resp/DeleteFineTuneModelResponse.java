package com.ai.openAi.endPoint.models.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteFineTuneModelResponse implements Serializable {

    private String id;
    private String object;
    private Boolean deleted;

}
