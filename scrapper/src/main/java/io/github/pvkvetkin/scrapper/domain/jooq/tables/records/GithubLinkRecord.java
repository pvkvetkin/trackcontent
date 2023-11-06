/*
 * This file is generated by jOOQ.
 */
package io.github.pvkvetkin.scrapper.domain.jooq.tables.records;


import io.github.pvkvetkin.scrapper.domain.jooq.tables.GithubLink;

import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;


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
public class GithubLinkRecord extends TableRecordImpl<GithubLinkRecord> implements Record4<Long, OffsetDateTime, Integer, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>GITHUB_LINK.LINK_ID</code>.
     */
    public void setLinkId(@Nullable Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>GITHUB_LINK.LINK_ID</code>.
     */
    @Nullable
    public Long getLinkId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>GITHUB_LINK.LAST_COMMIT_AT</code>.
     */
    public void setLastCommitAt(@Nullable OffsetDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>GITHUB_LINK.LAST_COMMIT_AT</code>.
     */
    @Nullable
    public OffsetDateTime getLastCommitAt() {
        return (OffsetDateTime) get(1);
    }

    /**
     * Setter for <code>GITHUB_LINK.ISSUES_COUNT</code>.
     */
    public void setIssuesCount(@Nullable Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>GITHUB_LINK.ISSUES_COUNT</code>.
     */
    @Nullable
    public Integer getIssuesCount() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>GITHUB_LINK.UPDATED_AT</code>.
     */
    public void setUpdatedAt(@Nullable OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>GITHUB_LINK.UPDATED_AT</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedAt() {
        return (OffsetDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row4<Long, OffsetDateTime, Integer, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row4<Long, OffsetDateTime, Integer, OffsetDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return GithubLink.GITHUB_LINK.LINK_ID;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field2() {
        return GithubLink.GITHUB_LINK.LAST_COMMIT_AT;
    }

    @Override
    @NotNull
    public Field<Integer> field3() {
        return GithubLink.GITHUB_LINK.ISSUES_COUNT;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field4() {
        return GithubLink.GITHUB_LINK.UPDATED_AT;
    }

    @Override
    @Nullable
    public Long component1() {
        return getLinkId();
    }

    @Override
    @Nullable
    public OffsetDateTime component2() {
        return getLastCommitAt();
    }

    @Override
    @Nullable
    public Integer component3() {
        return getIssuesCount();
    }

    @Override
    @Nullable
    public OffsetDateTime component4() {
        return getUpdatedAt();
    }

    @Override
    @Nullable
    public Long value1() {
        return getLinkId();
    }

    @Override
    @Nullable
    public OffsetDateTime value2() {
        return getLastCommitAt();
    }

    @Override
    @Nullable
    public Integer value3() {
        return getIssuesCount();
    }

    @Override
    @Nullable
    public OffsetDateTime value4() {
        return getUpdatedAt();
    }

    @Override
    @NotNull
    public GithubLinkRecord value1(@Nullable Long value) {
        setLinkId(value);
        return this;
    }

    @Override
    @NotNull
    public GithubLinkRecord value2(@Nullable OffsetDateTime value) {
        setLastCommitAt(value);
        return this;
    }

    @Override
    @NotNull
    public GithubLinkRecord value3(@Nullable Integer value) {
        setIssuesCount(value);
        return this;
    }

    @Override
    @NotNull
    public GithubLinkRecord value4(@Nullable OffsetDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    @NotNull
    public GithubLinkRecord values(@Nullable Long value1, @Nullable OffsetDateTime value2, @Nullable Integer value3, @Nullable OffsetDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached GithubLinkRecord
     */
    public GithubLinkRecord() {
        super(GithubLink.GITHUB_LINK);
    }

    /**
     * Create a detached, initialised GithubLinkRecord
     */
    @ConstructorProperties({ "linkId", "lastCommitAt", "issuesCount", "updatedAt" })
    public GithubLinkRecord(@Nullable Long linkId, @Nullable OffsetDateTime lastCommitAt, @Nullable Integer issuesCount, @Nullable OffsetDateTime updatedAt) {
        super(GithubLink.GITHUB_LINK);

        setLinkId(linkId);
        setLastCommitAt(lastCommitAt);
        setIssuesCount(issuesCount);
        setUpdatedAt(updatedAt);
    }

    /**
     * Create a detached, initialised GithubLinkRecord
     */
    public GithubLinkRecord(io.github.pvkvetkin.scrapper.domain.jooq.tables.pojos.GithubLink value) {
        super(GithubLink.GITHUB_LINK);

        if (value != null) {
            setLinkId(value.getLinkId());
            setLastCommitAt(value.getLastCommitAt());
            setIssuesCount(value.getIssuesCount());
            setUpdatedAt(value.getUpdatedAt());
        }
    }
}
