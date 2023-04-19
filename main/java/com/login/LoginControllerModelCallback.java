package com.login;

import com.dto.Account;

public interface LoginControllerModelCallback {
    void accountCreationSuccess(int accountNo);

    Account loginSuccess(Account account);

    void loginfail();
}
