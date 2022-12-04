package banking;

public abstract class Transaction {
	
	protected double amount;
	
	protected enum TransactionType {
		Withdrawal,
		Deposit
	}
	private TransactionType TransactionType;
	
	public Transaction(double amount) {
		setAmount(amount);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return TransactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		TransactionType = transactionType;
	}
	
	abstract void execute(Account account);
	
}
