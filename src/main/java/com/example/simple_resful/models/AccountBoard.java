package com.example.simple_resful.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "account_board")
public class AccountBoard implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Id
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @Column(name = "board_role")
    private String boardRole;
    @Column(name = "registered_date")
    private Date registeredDate;

    public void setBoardRole(String boardRole) {
        this.boardRole = boardRole;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getBoardRole() {
        return boardRole;
    }

    public Account getAccount() {
        return account;
    }

    public Board getBoard() {
        return board;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }
}
