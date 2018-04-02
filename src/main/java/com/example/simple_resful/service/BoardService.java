package com.example.simple_resful.service;

import com.example.simple_resful.form.AddBoardForm;
import com.example.simple_resful.models.Board;

public interface BoardService {
    Board getBoard(String boardMac);
    void save(AddBoardForm addBoardFrom);
}
