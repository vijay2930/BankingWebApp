package com.user;

import com.dto.Account;
import com.dto.MoneyRequest;
import com.dto.TransactionHistory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserControllerModelCallback {
    void passwordChangeSuccessfull(String password, Account account);

    void noTransactionsFound();

    void transactionsfound(List<TransactionHistory> trans);

    void showBalance(long balance, HttpServletRequest request, HttpServletResponse response);

    void requestAccFound(Account account);

    void requestAccNotFound();

    void requestSuccess(long amount, int accNo);

    void showRequest(List<MoneyRequest> requests);

    void noRequestFound();

    void requestPaymentSuccess(long amount, int fromAccNo);

    void requestPaymentFailed(long amount, int fromAccNo);
}
