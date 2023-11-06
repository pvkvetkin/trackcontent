package io.github.pvkvetkin.bot.service;

import com.pengrad.telegrambot.request.SendMessage;
import io.github.pvkvetkin.bot.wrapper.TelegramBotListener;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateService {

    private final TelegramBotListener botListener;

    public void handleUpdate(Long id, URI url, String description, List<Long> tgChatIds) {
        tgChatIds.forEach(
                chatId -> botListener.execute(new SendMessage(
                                chatId,
                                "Your link: " + url + " was updated\n" + description
                        ).disableWebPagePreview(true)
                )
        );
    }
}
