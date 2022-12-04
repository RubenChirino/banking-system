package main;

import java.util.Objects;

import javax.swing.JOptionPane;

import banking.ATM;
import banking.Account;
import banking.Bank;
import banking.CheckingAccount;
import banking.Client;
import banking.SavingAccount;
import banking.Storage;
import banking.Transaction;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Bank santanderBank = new Bank("Banco Santander"); 
		
		// ===== The bank set the ATMs =====
		ATM atm1 = new ATM(5000);
		santanderBank.addATM(atm1);
		
		// ===== Creating the client & account =====
		Client client1 = new Client("Ruben Chirino", "ruben@domain.com");
		Account account1 = new SavingAccount(1234, 500, "RU CH - VISA");
		account1.setLimit(250);
		Account account2 = new CheckingAccount(1065, 100000, "RU CH - American Express");
		account2.setLimit(3500);
		
		client1.addAccount(account1);
		client1.addAccount(account2);
		Storage.getInstance().addClient(client1);
		
		// ===== An user start to use the ATM =====
		atm1.welcomeScreen();
		
		// Get Client
		Client ATMClient = null;
		do {
			ATMClient = atm1.getEmailScreen();
			// Notification Message
			if (Objects.isNull(ATMClient)) {
				JOptionPane.showMessageDialog(
					null,
					"********    The email doesn't exists ********" + "\n\n"
				);
			}
		} while (Objects.isNull(ATMClient));
		
		// Get Bank Account
		Account ATMAccount = null;
		do {
			ATMAccount = atm1.selectBankAccountScreen(ATMClient);
			// Notification Message
			if (Objects.isNull(ATMAccount)) {
				JOptionPane.showMessageDialog(
					null,
					"********    You have entered an incorrect value, no account exists ********" + "\n\n"
				);
			}
		} while (Objects.isNull(ATMAccount));
		
		// Authentication
		boolean success = false;
		do {
			success = atm1.getAccountPINScreen(ATMAccount);
			// Notification Message
			if (!success) {
				JOptionPane.showMessageDialog(
					null,
					"********    Incorrect PIN ********" + "\n\n"
				);
			}
		} while (!success);
		
		// Operation
		Transaction ATMTransaction;
		do {
			ATMTransaction = atm1.getTransaction(ATMAccount);
		} while (Objects.isNull(ATMTransaction));
		
	
		JOptionPane.showMessageDialog(
			null,
			"********    The operation was successful  ********" + "\n\n"
			+ "Your current balance: " + ATMAccount.getFormattedMoney()
		);
		
	}

}
