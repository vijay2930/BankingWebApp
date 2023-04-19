package com.transferfund;

import com.dto.Account;

public class TransferController implements TransferControllerModelCallback{

    private TransferFundServlet tfServlet;
    private TransferModelCallback transferModel;


    public TransferController(TransferFundServlet tfServlet) {
        this.tfServlet=tfServlet;
        this.transferModel=new TransferModel(this);

    }


    public void checkTransferAccount(int accNo) {
        transferModel.checkTransferAccount( accNo);
    }


    public void checkAmount(long amount, int accNo, int senderAcc) {
        transferModel.checkAmount(amount,accNo,senderAcc);
    }


    public void transferAccFound(Account acc) {
      tfServlet.transferAccFound(acc);
    }


    public void transferAccNotFound() {
       tfServlet.transferAccNotFound();
    }


    public void amountSuccessfullySend(long amount, int accNo) {
        tfServlet.amountSuccessfullySend(amount,accNo);
    }

   
    public void inSufficientFunds() {
        tfServlet.inSufficientFunds();
    }
}
