package com.ai.openai.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 通用返回类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonListResponse<T> implements Serializable {
    private String object;
    private List<T> data;
    private Error error;
    @JsonProperty("has_more")
    private Boolean hasMore;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Error {
        private String message;
        private String type;
        private String param;
        private String code;
    }
}
