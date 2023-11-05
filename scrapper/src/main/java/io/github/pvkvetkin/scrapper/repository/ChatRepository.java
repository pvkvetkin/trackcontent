package io.github.pvkvetkin.scrapper.repository;

import io.github.pvkvetkin.scrapper.entity.Chat;
import java.util.List;

public interface ChatRepository {

    void add(Long id);

    void remove(Long id);

    List<Chat> findAll();
}
