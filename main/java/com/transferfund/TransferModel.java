package com.transferfund;

import com.dto.Account;
import com.repository.Repository;

public class TransferModel implements TransferModelCallback {

    private TransferControllerModelCallback transferController;
    private Repository data=Repository.getInstance();

    public TransferModel(TransferController transferController) {
        this.transferController=transferController;
    }

    @Override
    public void checkTransferAccount(int accNo) {
        Account acc=data.checkTransferAcc(accNo);
        if(acc!=null){
            transferController.transferAccFound(acc);
        }else {
            transferController.transferAccNotFound();
        }
    }

    @Override
    public void checkAmount(long amount, int accNo, int senderacc) {
        if(data.sendAmount(amount,accNo,senderacc)){
            transferController.amountSuccessfullySend(amount,accNo);
        }else{
            transferController.inSufficientFunds();
        }

    }
}
