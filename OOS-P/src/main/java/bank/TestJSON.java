/*package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionAttributeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJSON {


    public static void main(String[] args) throws TransactionAttributeException, TransactionAlreadyExistException, AccountAlreadyExistsException {
        PrivateBank privateBank = new PrivateBank("test", 0.05, 1, "testfiles");

        String acc = "testacc";
        OutgoingTransfer outgoingTransfer = new OutgoingTransfer("20.11.2023", 500, "Outgoing Transfer");
        OutgoingTransfer outgoingTransfer1 = new OutgoingTransfer(outgoingTransfer, "Alan", "Rekan");
        OutgoingTransfer outgoingTransfer3 = new OutgoingTransfer("20.11.2023",600,"ifif");
        OutgoingTransfer outgoingTransfer4 = new OutgoingTransfer(outgoingTransfer3,"Alan","Rekan");

        ArrayList<Transaction> list = new ArrayList<Transaction>(Arrays.asList(outgoingTransfer1, outgoingTransfer4));

        privateBank.createAccount(acc, list);
    }

}
*/