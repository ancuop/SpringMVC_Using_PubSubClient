package com.example.simple_resful.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {
    /* phai dung GenerationType.IDENTITY, neu dung AUTO thi se chay sai
    *  Hibernate-sequence doesn't exist */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "registered_date")
    private Date registeredDate = new Date();
    @OneToMany(targetEntity = AccountBoard.class, mappedBy = "account",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountBoard> accountBoards = new HashSet<>();

    public Account() {
    }

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // TODO follow ham nay de viet ham remove board
//    public void removeTag(Tag tag) {
//        for (Iterator<PostTag> iterator = tags.iterator();
//             iterator.hasNext(); ) {
//            PostTag postTag = iterator.next();
//
//            if (postTag.getPost().equals(this) &&
//                    postTag.getTag().equals(tag)) {
//                iterator.remove();
//                postTag.getTag().getPosts().remove(postTag);
//                postTag.setPost(null);
//                postTag.setTag(null);
//            }
//        }
//    }

    public void setId(int accountId) {
        this.id = accountId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAccountBoards(Set<AccountBoard> accountBoards) {
        this.accountBoards = accountBoards;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getId() {

        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Set<AccountBoard> getAccountBoards() {
        return accountBoards;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }
}
