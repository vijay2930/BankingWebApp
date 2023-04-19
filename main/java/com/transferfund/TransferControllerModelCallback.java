package com.transferfund;

import com.dto.Account;

public interface TransferControllerModelCallback {
    void transferAccFound(Account acc);

    void transferAccNotFound();

    void amountSuccessfullySend(long amount, int accNo);

    void inSufficientFunds();
}
