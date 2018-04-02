package com.example.simple_resful.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "board_mac", nullable = false, unique = true)
    private String boardMac; // mac address
    @Column(name = "board_name", nullable = false)
    private String boardName;   // mac address used to show board name. Can be revised
    @Column(name = "registered_date")
    private Date registeredDate;
    @OneToMany(targetEntity = AccountBoard.class, mappedBy = "board",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountBoard> accountBoards;

    //Must have this no-arg constructor. If not it will raise error "No default constructor for entity"
    public Board() {
        this.registeredDate = new Date();
    }

    public Board(String boardMac, String boardName) {
        this.boardMac = boardMac;
        this.boardName = boardName;
        this.registeredDate = new Date();
        this.accountBoards = new HashSet<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBoardMac(String boardMac) {
        this.boardMac = boardMac;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setAccountBoards(Set<AccountBoard> accountBoards) {
        this.accountBoards = accountBoards;
    }

    public int getId() {
        return this.id;
    }

    public String getBoardMac() {
        return this.boardMac;
    }

    public String getBoardName() {
        return this.boardName;
    }

    public Date getRegisteredDate() {
        return this.registeredDate;
    }

    public Set<AccountBoard> getAccountBoards() {
        return this.accountBoards;
    }
}
