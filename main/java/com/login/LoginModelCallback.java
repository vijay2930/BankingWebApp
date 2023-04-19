package com.login;

import com.dto.Account;

public interface LoginModelCallback {
    void createAccount(Account account);

    Account checkUser(int accNo, String password);

    void updateDetails();
}
