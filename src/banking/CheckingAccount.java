package banking;

public class CheckingAccount extends Account {

	public CheckingAccount(int pin, double money, String name) {
		super(pin, money, name);
		setAccountType(AccountType.Checking);
	}

	@Override
	double getMoney() {
		return money;
	}

	@Override
	void setMoney(double money) {
		if (money >= 0.0) {
			super.money = money;
		}
	}

}
