package com.example.simple_resful.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "board_id", nullable = false, unique = true)
    private String boardId; // mac address
    @Column(name = "board_name", nullable = false)
    private String boardName;   // mac address used to show board name. Can be revised
    @Column(name = "registered_date")
    private Date registeredDate;
    @OneToMany(targetEntity = AccountBoard.class, mappedBy = "board",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountBoard> accountBoards;

    //Must have this no-arg constructor. If not it will raise error "No default constructor for entity"
    public Board() {
    }

    public Board(String boardId) {
        this.boardId = boardId;
        this.boardName = boardId;
        this.registeredDate = new Date();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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
        return id;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public Set<AccountBoard> getAccountBoards() {
        return accountBoards;
    }
}
