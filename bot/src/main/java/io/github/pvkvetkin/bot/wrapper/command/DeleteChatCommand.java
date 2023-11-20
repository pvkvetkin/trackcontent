package io.github.pvkvetkin.bot.wrapper.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import io.github.pvkvetkin.bot.client.ScrapperClient;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeleteChatCommand implements Command {

    private final ScrapperClient scrapperClient;

    @Override
    public String command() {
        return "/delete";
    }

    @Override
    public String description() {
        return "Delete chat command";
    }

    @Override
    @Observed
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        scrapperClient.deleteChat(chatId);
        return new SendMessage(chatId, "Dear user, your chat has been deleted.");
    }
}
