package com.example.simple_resful.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AccountBoardRepositoryImpl implements AccountBoardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteAccountBoard(int accountId, int boardId) {
        String sql = "DELETE FROM account_board WHERE account_id=:accountId AND board_id=:boardId";
        entityManager.createNativeQuery(sql)
                .setParameter("accountId", accountId)
                .setParameter("boardId", boardId)
                .executeUpdate();
    }
}
