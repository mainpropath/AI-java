package com.ai.baidu.endPoint.chat.resp;

import com.ai.baidu.common.Usage;
import com.ai.baidu.endPoint.chat.SearchResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse implements Serializable {

    /**
     * 本轮对话的id
     */
    private String id;

    /**
     * 回包类型
     * chat.completion：多轮对话返回
     */
    private String Object;

    /**
     * 时间戳
     */
    private Integer created;

    /**
     * 表示当前子句的序号。只有在流式接口模式下会返回该字段
     */
    @JsonProperty("sentence_id")
    private Integer sentenceId;

    /**
     * 表示当前子句是否是最后一句。只有在流式接口模式下会返回该字段
     */
    @JsonProperty("is_end")
    private Boolean isEnd;

    /**
     * 当前生成的结果是否被截断
     */
    @JsonProperty("is_truncated")
    private Boolean isTruncated;

    /**
     * 输出内容标识，说明：
     * · normal：输出内容完全由大模型生成，未触发截断、替换
     * · stop：输出结果命中入参stop中指定的字段后被截断
     * · length：达到了最大的token数，根据EB返回结果is_truncated来截断
     * · content_filter：输出内容被截断、兜底、替换为**等
     */
    @JsonProperty("finish_reason")
    private String finishReason;

    /**
     * 搜索数据，当请求参数enable_citation为true并且触发搜索时，会返回该字段
     */
    @JsonProperty("search_info")
    private List<SearchResult> searchInfo;

    /**
     * 对话返回结果
     */
    private String result;

    /**
     * 表示用户输入是否存在安全，是否关闭当前会话，清理历史会话信息
     * true：是，表示用户输入存在安全风险，建议关闭当前会话，清理历史会话信息
     * false：否，表示用户输入无安全风险
     */
    @JsonProperty("need_clear_history")
    private Boolean needClearHistory;

    /**
     * 说明：
     * 0：正常返回
     * 其他：非正常
     */
    private Integer flag;

    /**
     * 当need_clear_history为true时，此字段会告知第几轮对话有敏感信息，如果是当前问题，ban_round=-1
     */
    @JsonProperty("ban_round")
    private Integer banRound;

    /**
     * token统计信息
     */
    private Usage usage;


}
