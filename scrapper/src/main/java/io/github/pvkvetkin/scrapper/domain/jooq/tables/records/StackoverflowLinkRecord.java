/*
 * This file is generated by jOOQ.
 */
package io.github.pvkvetkin.scrapper.domain.jooq.tables.records;


import io.github.pvkvetkin.scrapper.domain.jooq.tables.StackoverflowLink;

import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;


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
public class StackoverflowLinkRecord extends TableRecordImpl<StackoverflowLinkRecord> implements Record3<Long, Integer, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>STACKOVERFLOW_LINK.LINK_ID</code>.
     */
    public void setLinkId(@Nullable Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>STACKOVERFLOW_LINK.LINK_ID</code>.
     */
    @Nullable
    public Long getLinkId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>STACKOVERFLOW_LINK.ANSWER_COUNT</code>.
     */
    public void setAnswerCount(@Nullable Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>STACKOVERFLOW_LINK.ANSWER_COUNT</code>.
     */
    @Nullable
    public Integer getAnswerCount() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>STACKOVERFLOW_LINK.UPDATED_AT</code>.
     */
    public void setUpdatedAt(@Nullable OffsetDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>STACKOVERFLOW_LINK.UPDATED_AT</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedAt() {
        return (OffsetDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row3<Long, Integer, OffsetDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row3<Long, Integer, OffsetDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return StackoverflowLink.STACKOVERFLOW_LINK.LINK_ID;
    }

    @Override
    @NotNull
    public Field<Integer> field2() {
        return StackoverflowLink.STACKOVERFLOW_LINK.ANSWER_COUNT;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field3() {
        return StackoverflowLink.STACKOVERFLOW_LINK.UPDATED_AT;
    }

    @Override
    @Nullable
    public Long component1() {
        return getLinkId();
    }

    @Override
    @Nullable
    public Integer component2() {
        return getAnswerCount();
    }

    @Override
    @Nullable
    public OffsetDateTime component3() {
        return getUpdatedAt();
    }

    @Override
    @Nullable
    public Long value1() {
        return getLinkId();
    }

    @Override
    @Nullable
    public Integer value2() {
        return getAnswerCount();
    }

    @Override
    @Nullable
    public OffsetDateTime value3() {
        return getUpdatedAt();
    }

    @Override
    @NotNull
    public StackoverflowLinkRecord value1(@Nullable Long value) {
        setLinkId(value);
        return this;
    }

    @Override
    @NotNull
    public StackoverflowLinkRecord value2(@Nullable Integer value) {
        setAnswerCount(value);
        return this;
    }

    @Override
    @NotNull
    public StackoverflowLinkRecord value3(@Nullable OffsetDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    @NotNull
    public StackoverflowLinkRecord values(@Nullable Long value1, @Nullable Integer value2, @Nullable OffsetDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StackoverflowLinkRecord
     */
    public StackoverflowLinkRecord() {
        super(StackoverflowLink.STACKOVERFLOW_LINK);
    }

    /**
     * Create a detached, initialised StackoverflowLinkRecord
     */
    @ConstructorProperties({ "linkId", "answerCount", "updatedAt" })
    public StackoverflowLinkRecord(@Nullable Long linkId, @Nullable Integer answerCount, @Nullable OffsetDateTime updatedAt) {
        super(StackoverflowLink.STACKOVERFLOW_LINK);

        setLinkId(linkId);
        setAnswerCount(answerCount);
        setUpdatedAt(updatedAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised StackoverflowLinkRecord
     */
    public StackoverflowLinkRecord(io.github.pvkvetkin.scrapper.domain.jooq.tables.pojos.StackoverflowLink value) {
        super(StackoverflowLink.STACKOVERFLOW_LINK);

        if (value != null) {
            setLinkId(value.getLinkId());
            setAnswerCount(value.getAnswerCount());
            setUpdatedAt(value.getUpdatedAt());
            resetChangedOnNotNull();
        }
    }
}
