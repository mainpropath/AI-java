package com.ai.openAi.endPoint.images.resp;

import com.ai.openAi.endPoint.images.ImageObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateImageResponse {
    private Long created;
    private List<ImageObject> data;
}
