package com.ai.openai.endPoint.moderations.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModerationRequest {

    @NonNull
    private List<String> input;

    @Builder.Default
    private String model = Model.TEXT_MODERATION_LATEST.getName();

    /**
     * 构造基础请求参数
     *
     * @param input 文本
     * @return 请求参数
     */
    public static ModerationRequest baseBuild(String input) {
        ArrayList<String> list = new ArrayList<>();
        list.add(input);
        return baseBuild(list);
    }

    /**
     * 构造基础请求参数
     *
     * @param inputList 文本数组
     * @return 请求参数
     */
    public static ModerationRequest baseBuild(List<String> inputList) {
        return ModerationRequest.builder().input(inputList).build();
    }

    @Getter
    @AllArgsConstructor
    public enum Model {
        TEXT_MODERATION_STABLE("text-moderation-stable"),
        TEXT_MODERATION_LATEST("text-moderation-latest"),
        ;

        private final String name;
    }

}
