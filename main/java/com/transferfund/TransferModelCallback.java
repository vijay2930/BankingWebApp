package com.transferfund;

import com.dto.Account;

public interface TransferModelCallback {
    void checkTransferAccount(int accNo);

    void checkAmount(long amount, int accNo, int senderAcc);
}
