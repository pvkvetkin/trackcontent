package io.github.pvkvetkin.bot.wrapper.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import io.github.pvkvetkin.bot.client.ScrapperClient;
import io.github.pvkvetkin.bot.dto.request.RemoveLinksRequest;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UntrackCommand implements Command {

    private final ScrapperClient scrapperClient;

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "Remove untracked link command";
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        String link = update.message().text().split(" ")[1];
        scrapperClient.removeLinkTracking(chatId, new RemoveLinksRequest(URI.create(link)));
        return new SendMessage(chatId, "Dear user, your link has been added to tracking.");
    }
}
