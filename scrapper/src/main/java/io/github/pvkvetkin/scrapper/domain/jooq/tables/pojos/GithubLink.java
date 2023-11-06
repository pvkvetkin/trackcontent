/*
 * This file is generated by jOOQ.
 */
package io.github.pvkvetkin.scrapper.domain.jooq.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.Nullable;


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
public class GithubLink implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long linkId;
    private OffsetDateTime lastCommitAt;
    private Integer issuesCount;
    private OffsetDateTime updatedAt;

    public GithubLink() {}

    public GithubLink(GithubLink value) {
        this.linkId = value.linkId;
        this.lastCommitAt = value.lastCommitAt;
        this.issuesCount = value.issuesCount;
        this.updatedAt = value.updatedAt;
    }

    @ConstructorProperties({ "linkId", "lastCommitAt", "issuesCount", "updatedAt" })
    public GithubLink(
        @Nullable Long linkId,
        @Nullable OffsetDateTime lastCommitAt,
        @Nullable Integer issuesCount,
        @Nullable OffsetDateTime updatedAt
    ) {
        this.linkId = linkId;
        this.lastCommitAt = lastCommitAt;
        this.issuesCount = issuesCount;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>GITHUB_LINK.LINK_ID</code>.
     */
    @Nullable
    public Long getLinkId() {
        return this.linkId;
    }

    /**
     * Setter for <code>GITHUB_LINK.LINK_ID</code>.
     */
    public void setLinkId(@Nullable Long linkId) {
        this.linkId = linkId;
    }

    /**
     * Getter for <code>GITHUB_LINK.LAST_COMMIT_AT</code>.
     */
    @Nullable
    public OffsetDateTime getLastCommitAt() {
        return this.lastCommitAt;
    }

    /**
     * Setter for <code>GITHUB_LINK.LAST_COMMIT_AT</code>.
     */
    public void setLastCommitAt(@Nullable OffsetDateTime lastCommitAt) {
        this.lastCommitAt = lastCommitAt;
    }

    /**
     * Getter for <code>GITHUB_LINK.ISSUES_COUNT</code>.
     */
    @Nullable
    public Integer getIssuesCount() {
        return this.issuesCount;
    }

    /**
     * Setter for <code>GITHUB_LINK.ISSUES_COUNT</code>.
     */
    public void setIssuesCount(@Nullable Integer issuesCount) {
        this.issuesCount = issuesCount;
    }

    /**
     * Getter for <code>GITHUB_LINK.UPDATED_AT</code>.
     */
    @Nullable
    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>GITHUB_LINK.UPDATED_AT</code>.
     */
    public void setUpdatedAt(@Nullable OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final GithubLink other = (GithubLink) obj;
        if (this.linkId == null) {
            if (other.linkId != null)
                return false;
        }
        else if (!this.linkId.equals(other.linkId))
            return false;
        if (this.lastCommitAt == null) {
            if (other.lastCommitAt != null)
                return false;
        }
        else if (!this.lastCommitAt.equals(other.lastCommitAt))
            return false;
        if (this.issuesCount == null) {
            if (other.issuesCount != null)
                return false;
        }
        else if (!this.issuesCount.equals(other.issuesCount))
            return false;
        if (this.updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!this.updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.linkId == null) ? 0 : this.linkId.hashCode());
        result = prime * result + ((this.lastCommitAt == null) ? 0 : this.lastCommitAt.hashCode());
        result = prime * result + ((this.issuesCount == null) ? 0 : this.issuesCount.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GithubLink (");

        sb.append(linkId);
        sb.append(", ").append(lastCommitAt);
        sb.append(", ").append(issuesCount);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
