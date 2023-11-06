--changeset pvkvetkin:4
ALTER TABLE link
    ADD COLUMN IF NOT EXISTS link_type text
        CHECK ( link_type IN ('GITHUB', 'STACKOVERFLOW') );

--changeset pvkvetkin:5
CREATE TABLE IF NOT EXISTS github_link
(
    link_id        bigint UNIQUE ,
    last_commit_at timestamp with time zone,
    issues_count   int,
    updated_at     timestamp with time zone,
    FOREIGN KEY (link_id) REFERENCES link (id)
);

--changeset pvkvetkin:6
CREATE TABLE IF NOT EXISTS stackoverflow_link
(
    link_id      bigint,
    answer_count int,
    updated_at   timestamp with time zone,
    FOREIGN KEY (link_id) REFERENCES link (id)
);
