package com.ai.baidu.endPoint.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResult {

    /**
     * 序号
     */
    private Integer index;

    /**
     * 搜索结果URL
     */
    private String url;

    /**
     * 搜索结果标题
     */
    private String title;

}
