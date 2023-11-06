package io.github.pvkvetkin.bot.wrapper;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import io.github.pvkvetkin.bot.wrapper.command.Command;
import io.github.pvkvetkin.bot.wrapper.command.ReplyCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Component
public class UserMessageProcessor {

    private final List<Command> commands;

    public SendMessage process(Update update) {
        Optional<Command> maybeCommand = commands.stream()
            .filter(command -> command.supports(update) || checkReplyCommand(command, update))
            .findFirst();
        if (maybeCommand.isEmpty()) {
            return new SendMessage(
                update.message().chat().id(),
                "Unknown command. If you want to see all the command, please, type /help"
            );
        }
        return maybeCommand.get().handle(update);
    }

    boolean checkReplyCommand(Command command, Update update) {
        try {
            ReplyCommand replyCommand = (ReplyCommand) command;
            return replyCommand.canProcessReply(update);
        } catch (Exception e) {
            return false;
        }
    }
}
