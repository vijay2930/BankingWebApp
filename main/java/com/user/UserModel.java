package com.user;

import com.dto.Account;
import com.dto.MoneyRequest;
import com.dto.TransactionHistory;
import com.repository.Repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserModel implements UserModelCallback{

    private UserControllerModelCallback userController;
    private Repository data=Repository.getInstance();

    public UserModel(UserController userController) {
        this.userController=userController;
    }


    @Override
    public void changePassword(String password, Account account) {
        data.changePassword(password,account);
        userController.passwordChangeSuccessfull(password,account);
    }

    @Override
    public void showTransactions(Account account) {
        List<TransactionHistory> trans=data.getTransactions(account);
        if(trans.size()<1){
            userController.noTransactionsFound();
        }else{
            userController.transactionsfound(trans);
        }

    }

    @Override
    public void getBalance(Account account,HttpServletRequest request, HttpServletResponse response) {
       long balance= data.getBalance(account);
       userController.showBalance(balance,request,response);
    }

    @Override
    public void checkAcc(int accNo) {
       Account acc= data.checkTransferAcc(accNo);
        if(acc!=null){
            userController.requestAccFound(acc);
        }else {
            userController.requestAccNotFound();
        }
    }

    @Override
    public void requestMoney(long amount, int accNo, String description, int sender) {
       data.requestMoney(amount,accNo,description,sender);
       userController.requestSuccess(amount,accNo);
    }

    @Override
    public void findRequest(Account account) {
        List<MoneyRequest> requests=data.findMoneyRequest(account);
            userController.showRequest(requests);
       
    }

    @Override
    public void payRequest(long amount, int fromAccNo, Account account, int selectedId) {
        if(data.payRequest(amount,fromAccNo,account,selectedId)){
            userController.requestPaymentSuccess(amount,fromAccNo);
        }else{
            userController.requestPaymentFailed(amount,fromAccNo);
        }
    }
}
