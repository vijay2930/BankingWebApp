package com.user;

import com.dto.Account;
import com.dto.MoneyRequest;
import com.dto.TransactionHistory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController implements UserControllerModelCallback{

    private UserModelCallback userModel;
    private UserServlet userServlet;

    public UserController(UserServlet userServlet) {
    	this.userServlet=userServlet;
        this.userModel=new UserModel(this);
    }

    
    public void changePassword(String password, Account account) {
        userModel.changePassword(password,account);
    }

    
    public void showTransactions(Account account) {
        userModel.showTransactions(account);
    }


    public void getBalance(Account account, HttpServletRequest request, HttpServletResponse response) {
        userModel.getBalance(account,request,response);
    }

    
    public void checkAcc(int accNo) {
        userModel.checkAcc(accNo);
    }


    public void requestMoney(long amount, int accNo, String description, int sender) {
        userModel.requestMoney(amount,accNo,description,sender);
    }


    public void findRequest(Account account) {
        userModel.findRequest(account);
    }


    public void payRequest(long amount, int fromAccNo, Account account, int selectedId) {
        userModel.payRequest(amount,fromAccNo,account,selectedId);
    }

    @Override
    public void passwordChangeSuccessfull(String password, Account account) {
      //  userView.passwordChangeSuccessfull(password,account);
    }

    @Override
    public void noTransactionsFound() {
        //userView.noTransactionsFound();
    }

    @Override
    public void transactionsfound(List<TransactionHistory> trans) {
       userServlet.showTransactions(trans);
    }

    @Override
    public void showBalance(long balance,HttpServletRequest request, HttpServletResponse response) {
         userServlet.showBalance(balance,request,  response);
    }

    @Override
    public void requestAccFound(Account acc) {
       // userView.requestAccFound(accName,accNo);
    }

    @Override
    public void requestAccNotFound() {
       // userView.requestAccNotFound();
    }

    @Override
    public void requestSuccess(long amount, int accNo) {
        userServlet.requestSucess(amount,accNo);
    }

    @Override
    public void showRequest(List<MoneyRequest> requests) {
       userServlet.showRequest(requests);
    }

    @Override
    public void noRequestFound() {
        //userView.noRequestFound();
    }

    @Override
    public void requestPaymentSuccess(long amount, int fromAccNo) {
      //  userView.requestPaymentSuccess(amount,fromAccNo);
    }

    @Override
    public void requestPaymentFailed(long amount, int fromAccNo) {
       // userView.requestPaymentFailed(amount,fromAccNo);
    }
}
