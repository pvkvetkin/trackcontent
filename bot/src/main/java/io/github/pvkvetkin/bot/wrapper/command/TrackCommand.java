package io.github.pvkvetkin.bot.wrapper.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import io.github.pvkvetkin.bot.client.ScrapperClient;
import io.github.pvkvetkin.bot.dto.request.AddLinksRequest;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TrackCommand implements Command, ReplyCommand {

    private final ScrapperClient scrapperClient;

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Start tracking command";
    }

    @Override
    public String replyText() {
        return "Send a link to the site you want to track";
    }

    @Override
    public SendMessage requestReply(Update update) {
        return new SendMessage(update.message().chat().id(), replyText())
                .replyMarkup(new ForceReply());
    }

    @Override
    public SendMessage handle(Update update) {
        if (!canProcessReply(update)) {
            return requestReply(update);
        }

        Long id = update.message().chat().id();
        String link = update.message().text();
        try {
            URI uriLink = URI.create(link);
            scrapperClient.addLinkTracking(id, new AddLinksRequest(uriLink));
        } catch (Exception ex) {
            return new SendMessage(id, "Error was occurred while adding link to the tracking list.");
        }
        return new SendMessage(id, "Link was successfully added to the tracking list.");
    }
}
