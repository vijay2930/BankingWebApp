package com.login;

import com.dto.Account;
import com.repository.Repository;

public class LoginModel implements LoginModelCallback{

    private LoginControllerModelCallback loginController;
    private Repository data=Repository.getInstance();

    public LoginModel(LoginController loginController) {
        this.loginController=loginController;
    }

    @Override
    public void createAccount(Account account) {
        int accountNo=data.createAccount(account);
        if(accountNo!=0){
            loginController.accountCreationSuccess(accountNo);
        }
    }

    @Override
    public Account checkUser(int accNo, String password) {
        Account account=data.checkUser(accNo,password);
        return account;
    }

    @Override
    public void updateDetails() {
        data.updateDetails();
    }
}
