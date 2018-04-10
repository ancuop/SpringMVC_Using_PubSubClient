package com.example.simple_resful.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountBoardId implements Serializable {
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "board_id")
    private int boardId;

    private AccountBoardId() {
    }

    public AccountBoardId(int accountId, int boardId) {
        this.accountId = accountId;
        this.boardId = boardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AccountBoardId that = (AccountBoardId) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(boardId, that.boardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, boardId);
    }
}
