/*
 * This file is generated by jOOQ.
 */
package io.github.pvkvetkin.scrapper.domain.jooq;


import io.github.pvkvetkin.scrapper.domain.jooq.tables.Chat;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.GithubLink;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.Link;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.LinkChat;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.StackoverflowLink;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.records.ChatRecord;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.records.GithubLinkRecord;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.records.LinkChatRecord;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.records.LinkRecord;
import io.github.pvkvetkin.scrapper.domain.jooq.tables.records.StackoverflowLinkRecord;

import javax.annotation.processing.Generated;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ChatRecord> CONSTRAINT_1 = Internal.createUniqueKey(Chat.CHAT, DSL.name("CONSTRAINT_1"), new TableField[] { Chat.CHAT.ID }, true);
    public static final UniqueKey<GithubLinkRecord> CONSTRAINT_D = Internal.createUniqueKey(GithubLink.GITHUB_LINK, DSL.name("CONSTRAINT_D"), new TableField[] { GithubLink.GITHUB_LINK.LINK_ID }, true);
    public static final UniqueKey<LinkRecord> CONSTRAINT_2 = Internal.createUniqueKey(Link.LINK, DSL.name("CONSTRAINT_2"), new TableField[] { Link.LINK.ID }, true);
    public static final UniqueKey<LinkRecord> CONSTRAINT_23 = Internal.createUniqueKey(Link.LINK, DSL.name("CONSTRAINT_23"), new TableField[] { Link.LINK.URL }, true);
    public static final UniqueKey<LinkChatRecord> CONSTRAINT_470 = Internal.createUniqueKey(LinkChat.LINK_CHAT, DSL.name("CONSTRAINT_470"), new TableField[] { LinkChat.LINK_CHAT.LINK_ID, LinkChat.LINK_CHAT.CHAT_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<GithubLinkRecord, LinkRecord> CONSTRAINT_D8 = Internal.createForeignKey(GithubLink.GITHUB_LINK, DSL.name("CONSTRAINT_D8"), new TableField[] { GithubLink.GITHUB_LINK.LINK_ID }, Keys.CONSTRAINT_2, new TableField[] { Link.LINK.ID }, true);
    public static final ForeignKey<LinkChatRecord, LinkRecord> CONSTRAINT_4 = Internal.createForeignKey(LinkChat.LINK_CHAT, DSL.name("CONSTRAINT_4"), new TableField[] { LinkChat.LINK_CHAT.LINK_ID }, Keys.CONSTRAINT_2, new TableField[] { Link.LINK.ID }, true);
    public static final ForeignKey<LinkChatRecord, ChatRecord> CONSTRAINT_47 = Internal.createForeignKey(LinkChat.LINK_CHAT, DSL.name("CONSTRAINT_47"), new TableField[] { LinkChat.LINK_CHAT.CHAT_ID }, Keys.CONSTRAINT_1, new TableField[] { Chat.CHAT.ID }, true);
    public static final ForeignKey<StackoverflowLinkRecord, LinkRecord> CONSTRAINT_9 = Internal.createForeignKey(StackoverflowLink.STACKOVERFLOW_LINK, DSL.name("CONSTRAINT_9"), new TableField[] { StackoverflowLink.STACKOVERFLOW_LINK.LINK_ID }, Keys.CONSTRAINT_2, new TableField[] { Link.LINK.ID }, true);
}
