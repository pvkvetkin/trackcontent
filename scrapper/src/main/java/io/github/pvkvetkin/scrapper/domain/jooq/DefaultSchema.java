/*
 * This file is generated by jOOQ.
 */
package io.github.pvkvetkin.scrapper.domain.jooq;


import io.github.pvkvetkin.scrapper.domain.jooq.tables.Chat;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.GithubLink;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.Link;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.LinkChat;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.StackoverflowLink;

import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>CHAT</code>.
     */
    public final Chat CHAT = Chat.CHAT;

    /**
     * The table <code>GITHUB_LINK</code>.
     */
    public final GithubLink GITHUB_LINK = GithubLink.GITHUB_LINK;

    /**
     * The table <code>LINK</code>.
     */
    public final Link LINK = Link.LINK;

    /**
     * The table <code>LINK_CHAT</code>.
     */
    public final LinkChat LINK_CHAT = LinkChat.LINK_CHAT;

    /**
     * The table <code>STACKOVERFLOW_LINK</code>.
     */
    public final StackoverflowLink STACKOVERFLOW_LINK = StackoverflowLink.STACKOVERFLOW_LINK;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    @NotNull
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    @NotNull
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Chat.CHAT,
            GithubLink.GITHUB_LINK,
            Link.LINK,
            LinkChat.LINK_CHAT,
            StackoverflowLink.STACKOVERFLOW_LINK
        );
    }
}
