package io.github.pvkvetkin.scrapper.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chat {

    @Id
    @Column(name = "id")
    private Long id;
    @ManyToMany(mappedBy = "chats")
    private List<Link> links = new ArrayList<>();
}
