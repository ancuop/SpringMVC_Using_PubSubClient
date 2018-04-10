package com.example.simple_resful.service;

import com.example.simple_resful.form.AddBoardForm;
import com.example.simple_resful.models.Account;
import com.example.simple_resful.models.AccountBoard;
import com.example.simple_resful.models.Board;
import com.example.simple_resful.repository.AccountRepository;
import com.example.simple_resful.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Board getBoard(String boardMac) {
        return boardRepository.findByBoardMac(boardMac);
    }

    // this is use for ADMIN add board
    @Override
    @Transactional
    public void save(AddBoardForm addBoardFrom) {
        String boardName = addBoardFrom.getBoardName().isEmpty() == true ?
                addBoardFrom.getBoardMac() : addBoardFrom.getBoardName();
        Board board = new Board(addBoardFrom.getBoardMac(), boardName);
        boardRepository.save(board);
        Account account = accountRepository.findByUsername(addBoardFrom.getAdminName());
        AccountBoard accountBoard = new AccountBoard(account, board, "BOARD_ADMIN");
        account.getAccountBoards().add(accountBoard);
        accountRepository.save(account);

    }
}
