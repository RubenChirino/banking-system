package banking;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public abstract class Account {
	
	protected String accountNumber;
	protected int pin;
	protected String name;
	protected double money;
	protected double limit;
	protected ArrayList<Transaction> transactions;
	
	protected enum AccountType {
		Saving,
		Checking
	}
	private AccountType AccountType;
	
	public Account(int pin, double money, String name) {
		setAccountNumber(Util.generateBankAccountNumber());
		this.setPin(pin);
		this.setName(name);
		this.setMoney(money);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	private void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public AccountType getAccountType() {
		return AccountType;
	}

	public void setAccountType(AccountType accountType) {
		AccountType = accountType;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}
	
	public String getFormattedMoney() {
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(getMoney());
	}

	abstract double getMoney();

	abstract void setMoney(double money);

}
