package io.github.pvkvetkin.bot.wrapper.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HelpCommand implements Command {

    private final List<Command> commandList;

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "Help command";
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        StringBuilder sb = new StringBuilder();
        commandList.forEach(command -> sb.append(
                command.command()).append(" - ").append(command.description()).append("\n")
        );
        return new SendMessage(chatId, sb.toString());
    }
}
