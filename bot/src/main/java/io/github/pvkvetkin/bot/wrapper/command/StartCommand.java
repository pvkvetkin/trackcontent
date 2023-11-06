package io.github.pvkvetkin.bot.wrapper.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import io.github.pvkvetkin.bot.client.ScrapperClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartCommand implements Command {

    private final ScrapperClient scrapperClient;

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Register user command";
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        scrapperClient.registerChat(chatId);
        return new SendMessage(chatId,
                """
                        Hello dear user!\s
                        I'm a bot that can track links for you.\s
                        To start tracking, send me a link to the site you want to track.
                        """
        );
    }
}
