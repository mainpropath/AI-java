package com.ai.spark.achieve.defaults.listener;

import com.ai.common.utils.JsonUtils;
import com.ai.spark.common.Usage;
import com.ai.spark.endPoint.chat.ChatHeader;
import com.ai.spark.endPoint.chat.Choice;
import com.ai.spark.endPoint.chat.req.ChatRequest;
import com.ai.spark.endPoint.chat.resp.ChatResponse;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.ai.common.utils.ValidationUtils.ensureNotNull;


@Slf4j
@Data
public abstract class ChatListener extends WebSocketListener {

    /**
     * 请求大模型的参数
     */
    private ChatRequest chatRequest;

    public ChatListener(ChatRequest chatRequest) {
        this.chatRequest = ensureNotNull(chatRequest, "chatRequest");
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
     * @param chatResponse 大模型返回值
     */
    public abstract void onChatError(ChatResponse chatResponse);

    /**
     * 星火大模型正常返回信息
     *
     * @param chatResponse 大模型返回值
     */
    public abstract void onChatOutput(ChatResponse chatResponse);

    /**
     * 星火大模型返回信息结束回调
     */
    public abstract void onChatEnd();

    /**
     * 星火大模型本次请求消耗的Token信息
     *
     * @param usage 大模型返回token信息
     */
    public abstract void onChatToken(Usage usage);

    @Override
    public final void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public final void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public final void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        webSocket.close(1000, "");
        this.onWebSocketError(t, response);
    }

    @Override
    public final void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        System.out.println(text);
        ChatResponse chatResponse = JsonUtils.fromJson(text, ChatResponse.class);

        if (ChatHeader.Code.SUCCESS.getValue() != chatResponse.getChatHeader().getCode()) {
            log.warn("调用星火模型发生错误，错误码为：{}，请求的sid为：{}", chatResponse.getChatHeader().getCode(), chatResponse.getChatHeader().getSid());
            webSocket.close(1000, "星火模型调用异常");
            this.onChatError(chatResponse);
            return;
        }

        this.onChatOutput(chatResponse);

        if (Choice.Status.END.getValue() == chatResponse.getChatHeader().getStatus()) {
            // 可以关闭连接，释放资源
            webSocket.close(1000, "星火模型返回结束");
            Usage usage = chatResponse.getChatPayload().getUsage();
            this.onChatEnd();
            this.onChatToken(usage);
        }
    }

    @Override
    public final void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @SneakyThrows
    @Override
    public final void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        webSocket.send(JsonUtils.toJson(chatRequest));
    }
}
