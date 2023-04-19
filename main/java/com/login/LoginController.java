package com.login;

import com.dto.Account;

public class LoginController implements LoginControllerModelCallback{

    private LoginModelCallback loginmodel;
    private LoginServlet ls;

    public LoginController(LoginServlet ls) {
    	this.ls=ls;
        this.loginmodel=new LoginModel(this);
    }

   
    public void createAccount(Account account) {
        loginmodel.createAccount(account);
    }

 
    public Account checkUser(int accNo, String password) {
         return loginmodel.checkUser(accNo,password);
    }


    public void updateDetails() {
        loginmodel.updateDetails();
    }

    @Override
    public void accountCreationSuccess(int accountNo) {
       ls.accountCreationSuccess(accountNo);
    }

    @Override
    public Account loginSuccess(Account account) {
    	return account;
       // loginview.loginSuccess(account);
    }

    @Override
    public void loginfail() {
       // loginview.loginFail();
    }
}
