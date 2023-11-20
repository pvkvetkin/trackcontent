package io.github.pvkvetkin.bot.wrapper.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import io.github.pvkvetkin.bot.client.ScrapperClient;
import io.github.pvkvetkin.bot.dto.response.LinkResponse;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ListCommand implements Command {

    private final ScrapperClient scrapperClient;

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "Get tracked links command";
    }

    @Override
    @Observed
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        List<LinkResponse> linkResponses = scrapperClient.getAllTrackedLinks(chatId).items();

        if (linkResponses.isEmpty()) {
            return new SendMessage(chatId, "Dear user, your list of tracked links is empty.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Tracked links:\n");
        for (LinkResponse linkResponse : linkResponses) {
            sb.append(linkResponse.url()).append("\n");
        }
        return new SendMessage(chatId, sb.toString());
    }
}
