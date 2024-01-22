package com.ai.spark.achieve.defaults.listener;

import com.ai.common.utils.JsonUtils;
import com.ai.spark.endPoint.chat.ChatHeader;
import com.ai.spark.endPoint.chat.Choice;
import com.ai.spark.endPoint.chat.resp.DocumentChatResponse;
import com.ai.spark.endPoint.images.ImageHeader;
import com.ai.spark.endPoint.images.req.ImageUnderstandingRequest;
import com.ai.spark.endPoint.images.resp.ImageUnderstandingResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;

/**
 * 图片理解对话监听器
 */
@Slf4j
@Data
public abstract class ImageUnderstandingListener extends BaseListener<ImageUnderstandingRequest, ImageUnderstandingResponse> {

    private ImageUnderstandingRequest imageUnderstandingRequest;

    /**
     * 构造方法，传入大模型参数
     *
     * @param imageUnderstandingRequest 大模型参数
     */
    public ImageUnderstandingListener(ImageUnderstandingRequest imageUnderstandingRequest) {
        this.imageUnderstandingRequest = ensureNotNull(imageUnderstandingRequest, "documentChatRequest");
    }

    /**
     * WebSocket服务发生异常的回调，可以覆盖重写。
     * 默认抛出异常
     *
     * @param t        异常
     * @param response 返回值
     */
    public void onWebSocketError(Throwable t, Response response) {
        log.error("调用星火模型时，WebSocket发生异常:{}", response);
        t.printStackTrace();
    }

    /**
     * 星火大模型发生异常
     *
     * @param imageUnderstandingResponse 大模型返回值
     */
    public abstract void onChatError(ImageUnderstandingResponse imageUnderstandingResponse);

    /**
     * 星火大模型正常返回信息
     *
     * @param imageUnderstandingResponse 大模型返回值
     */
    public abstract void onChatOutput(ImageUnderstandingResponse imageUnderstandingResponse);

    /**
     * 星火大模型返回信息结束回调
     */
    public abstract void onChatEnd();

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        webSocket.close(1000, "");
        this.onWebSocketError(t, response);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        ImageUnderstandingResponse imageUnderstandingResponse = JsonUtils.fromJson(text, ImageUnderstandingResponse.class);

        ImageHeader imageHeader = imageUnderstandingResponse.getImageHeader();
        if (imageHeader.getCode() != ChatHeader.Code.SUCCESS.getValue()) {
            log.warn("调用星火模型文档对话发生错误，错误码为：{}，请求的sid为：{}", imageHeader.getCode(), imageHeader.getSid());
            webSocket.close(1000, "星火模型调用异常");
            this.onChatError(imageUnderstandingResponse);
            return;
        }

        this.onChatOutput(imageUnderstandingResponse);

        if (DocumentChatResponse.Status.END.getValue() == Choice.Status.END.getValue()) {
            // 可以关闭连接，释放资源
            webSocket.close(1000, "星火模型返回结束");
            this.onChatEnd();
        }
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        webSocket.send(JsonUtils.toJson(imageUnderstandingRequest));
    }
}
