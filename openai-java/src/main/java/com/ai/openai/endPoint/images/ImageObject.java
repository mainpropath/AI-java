package com.ai.openai.endPoint.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageObject {

    private String url;
    @JsonProperty("b64_json")
    private String b64Json;
    @JsonProperty("revised_prompt")
    private String revisedPrompt;


}
