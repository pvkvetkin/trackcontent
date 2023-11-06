/*
 * This file is generated by jOOQ.
 */
package io.github.pvkvetkin.scrapper.domain.jooq.tables.records;


import io.github.pvkvetkin.scrapper.domain.jooq.tables.Link;

import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LinkRecord extends UpdatableRecordImpl<LinkRecord> implements Record5<Long, String, OffsetDateTime, OffsetDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LINK.ID</code>.
     */
    public void setId(@NotNull Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>LINK.ID</code>.
     */
    @NotNull
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LINK.URL</code>.
     */
    public void setUrl(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LINK.URL</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 1000000000)
    @NotNull
    public String getUrl() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LINK.LAST_CHECK_AT</code>.
     */
    public void setLastCheckAt(@NotNull OffsetDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>LINK.LAST_CHECK_AT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public OffsetDateTime getLastCheckAt() {
        return (OffsetDateTime) get(2);
    }

    /**
     * Setter for <code>LINK.UPDATED_AT</code>.
     */
    public void setUpdatedAt(@NotNull OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>LINK.UPDATED_AT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public OffsetDateTime getUpdatedAt() {
        return (OffsetDateTime) get(3);
    }

    /**
     * Setter for <code>LINK.LINK_TYPE</code>.
     */
    public void setLinkType(@Nullable String value) {
        set(4, value);
    }

    /**
     * Getter for <code>LINK.LINK_TYPE</code>.
     */
    @Size(max = 1000000000)
    @Nullable
    public String getLinkType() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row5<Long, String, OffsetDateTime, OffsetDateTime, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row5<Long, String, OffsetDateTime, OffsetDateTime, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return Link.LINK.ID;
    }

    @Override
    @NotNull
    public Field<String> field2() {
        return Link.LINK.URL;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field3() {
        return Link.LINK.LAST_CHECK_AT;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field4() {
        return Link.LINK.UPDATED_AT;
    }

    @Override
    @NotNull
    public Field<String> field5() {
        return Link.LINK.LINK_TYPE;
    }

    @Override
    @NotNull
    public Long component1() {
        return getId();
    }

    @Override
    @NotNull
    public String component2() {
        return getUrl();
    }

    @Override
    @NotNull
    public OffsetDateTime component3() {
        return getLastCheckAt();
    }

    @Override
    @NotNull
    public OffsetDateTime component4() {
        return getUpdatedAt();
    }

    @Override
    @Nullable
    public String component5() {
        return getLinkType();
    }

    @Override
    @NotNull
    public Long value1() {
        return getId();
    }

    @Override
    @NotNull
    public String value2() {
        return getUrl();
    }

    @Override
    @NotNull
    public OffsetDateTime value3() {
        return getLastCheckAt();
    }

    @Override
    @NotNull
    public OffsetDateTime value4() {
        return getUpdatedAt();
    }

    @Override
    @Nullable
    public String value5() {
        return getLinkType();
    }

    @Override
    @NotNull
    public LinkRecord value1(@NotNull Long value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value2(@NotNull String value) {
        setUrl(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value3(@NotNull OffsetDateTime value) {
        setLastCheckAt(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value4(@NotNull OffsetDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value5(@Nullable String value) {
        setLinkType(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord values(@NotNull Long value1, @NotNull String value2, @NotNull OffsetDateTime value3, @NotNull OffsetDateTime value4, @Nullable String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LinkRecord
     */
    public LinkRecord() {
        super(Link.LINK);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    @ConstructorProperties({ "id", "url", "lastCheckAt", "updatedAt", "linkType" })
    public LinkRecord(@NotNull Long id, @NotNull String url, @NotNull OffsetDateTime lastCheckAt, @NotNull OffsetDateTime updatedAt, @Nullable String linkType) {
        super(Link.LINK);

        setId(id);
        setUrl(url);
        setLastCheckAt(lastCheckAt);
        setUpdatedAt(updatedAt);
        setLinkType(linkType);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    public LinkRecord(io.github.pvkvetkin.scrapper.domain.jooq.tables.pojos.Link value) {
        super(Link.LINK);

        if (value != null) {
            setId(value.getId());
            setUrl(value.getUrl());
            setLastCheckAt(value.getLastCheckAt());
            setUpdatedAt(value.getUpdatedAt());
            setLinkType(value.getLinkType());
        }
    }
}