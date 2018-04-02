package com.example.simple_resful.form;

import org.hibernate.validator.constraints.NotEmpty;

public class AddBoardForm {
    @NotEmpty
    private String boardMac;
    private String boardName;
    private String adminName;

    public AddBoardForm() {
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setBoardMac(String boardMac) {
        this.boardMac = boardMac;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardMac() {
        return boardMac;
    }

    public String getBoardName() {
        return boardName;
    }
}
