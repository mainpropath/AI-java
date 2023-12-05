package com.ai.openAi.endPoint.moderations.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ModerationRequest {

    @NonNull
    private List<String> input;

    @Builder.Default
    private String model = Model.TEXT_MODERATION_LATEST.getName();

    @Getter
    @AllArgsConstructor
    public enum Model {
        TEXT_MODERATION_STABLE("text-moderation-stable"),
        TEXT_MODERATION_LATEST("text-moderation-latest"),
        ;

        private final String name;
    }

    /**
     * 构造基础请求参数
     *
     * @param input 文本
     * @return 请求参数
     */
    public static ModerationRequest BuildBaseModerationRequest(String input) {
        return BuildBaseModerationRequest(Arrays.asList(input));
    }

    /**
     * 构造基础请求参数
     *
     * @param inputList 文本数组
     * @return 请求参数
     */
    public static ModerationRequest BuildBaseModerationRequest(List<String> inputList) {
        return ModerationRequest.builder().input(inputList).build();
    }

}
