package banking;

public class Withdrawal extends Transaction {

	public Withdrawal(double amount) {
		super(amount);
	}

	@Override
	void execute(Account account) {
		account.setMoney(account.getMoney() - getAmount());
	}
	
}
