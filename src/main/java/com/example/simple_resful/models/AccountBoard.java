package com.example.simple_resful.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "account_board")
public class AccountBoard implements Serializable {

    @EmbeddedId
    private AccountBoardId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    private Board board;

    @Column(name = "board_role")
    private String boardRole;

    @Column(name = "registered_date")
    private Date registeredDate = new Date();

    private AccountBoard() {
    }

    public AccountBoard(Account account, Board board, String boardRole) {
        this.account = account;
        this.board = board;
        this.boardRole = boardRole;
        this.id = new AccountBoardId(account.getId(), board.getId());
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setBoardRole(String boardRole) {
        this.boardRole = boardRole;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Account getAccount() {
        return account;
    }

    public Board getBoard() {
        return board;
    }

    public String getBoardRole() {
        return boardRole;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }
}
