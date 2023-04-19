package com.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Account;

public interface UserModelCallback {

    void changePassword(String password, Account account);

    void showTransactions(Account account);

    void getBalance(Account account, HttpServletRequest request, HttpServletResponse response);

    void checkAcc(int accNo);

    void requestMoney(long amount, int accNo, String description, int sender);

    void findRequest(Account account);

    void payRequest(long amount, int fromAccNo, Account account, int selectedId);
}
