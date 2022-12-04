package banking;

public class Deposit extends Transaction {

	public Deposit(double amount) {
		super(amount);
	}

	@Override
	void execute(Account account) {
		account.setMoney(account.getMoney() + getAmount());
	}

}
