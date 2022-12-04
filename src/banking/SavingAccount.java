package banking;

public class SavingAccount extends Account {

	public SavingAccount(int pin, double money, String name) {
		super(pin, money, name);
		setAccountType(AccountType.Saving);
	}

	@Override
	public void setMoney(double money) {
		if (money >= 0.0) {
			super.money = money;
		}
	}

	@Override
	double getMoney() {
		return super.money;
	}
	
}
