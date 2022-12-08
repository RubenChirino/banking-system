package banking;

import java.util.Objects;
import java.util.UUID;

import javax.swing.JOptionPane;

public class ATM {
	
	private UUID uuid;
	private double money;
	
	public ATM(double money) {
		setUuid(UUID.randomUUID());
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	/* === Screen === */
	
	public void welcomeScreen() {
		JOptionPane.showMessageDialog(
			null,
			"********    Welcome to the ATM  ********" + "\n\n"
		);
	}
	
	public Client getEmailScreen() {
		String email = (String) JOptionPane.showInputDialog(
			null,
			"********    Enter your account email  ********" + "\n\n"
			+ "Note: You can enter 0 if you want to exit"
		);
		
		if (!Objects.isNull(email) && email.length() == 1 && Integer.parseInt(email) == 0) {
			JOptionPane.showMessageDialog(
				null,
				"********    Thanks for use our system!   ********" + "\n\n"
			);
			System.exit(0);
		}
		
		return Storage.getInstance().getClient(email);
	}
	
	public Account selectBankAccountScreen(Client client) {
		StringBuilder message = new StringBuilder();
		for(int i = 0; i < client.getAccounts().size(); i++) {
			Account account = client.getAccounts().get(i);
			message.append((i + 1) + "- " + account.getAccountNumber() + " (" + account.getName() + " " + account.getAccountType() + " Account" + ")");
			message.append("\n");
		}
		
		String val = JOptionPane.showInputDialog(
			null,
			"********    Select your Account  ********" + "\n\n"
			+ message + "\n\n"
			+ "Note: To select the account you want to use by adding the number before the account number in the input." + "\n\n"
			+ "You can enter 0 if you want to exit"
		);
		
		if (!Objects.isNull(val) && val.length() == 1 && Integer.parseInt(val) == 0) {
			JOptionPane.showMessageDialog(
				null,
				"********    Thanks for use our system!   ********" + "\n\n"
			);
			System.exit(0);
		}
		
		try {
			Account account = client.getAccounts().get(Integer.parseInt(val) - 1);
			return account;
		}
		catch(Exception e) {
			return null;
		}
	}

	
	public boolean getAccountPINScreen(Account account) {
		String pin = JOptionPane.showInputDialog(
			null,
			"********    Enter your Account PIN  ********" + "\n\n"
			+ "Note: You can enter 0 if you want to exit"
		);
		if (!Objects.isNull(pin)  && pin != "" && pin.length() > 0) {
			if (pin.length() == 1 && Integer.parseInt(pin) == 0) {
				JOptionPane.showMessageDialog(
					null,
					"********    Thanks for use our system!   ********" + "\n\n"
				);
				System.exit(0);
			}
			if (account.getPin() == Integer.parseInt(pin)) {
				return true; 
			}
		}
		return false;
	}

	public Transaction getDoTransactionScreen(Account account) {
		    
		String type;
		Transaction transaction = null;
		boolean TypeTransactionError;
		
		do {
			TypeTransactionError = false;
			
			type = JOptionPane.showInputDialog(
					null,
					"********    Select the transaction type  ********" + "\n\n"
					+ "1- Deposit" + "\n\n"
					+ "2- Withdrawal" + "\n\n"
					+ "Your current balance: " + account.getFormattedMoney() + "\n\n"
					+ "Note: You can enter 0 if you want to exit"
				);
			
			if (!Objects.isNull(type) && type.length() == 1) {
				switch(Integer.parseInt(type)) {
				  case 0:
					  JOptionPane.showMessageDialog(
							null,
							"********    Thanks for use our system!   ********" + "\n\n"
					  );
					  System.exit(0);
				    break;
				  case 1:
					  transaction = new Deposit(0);
				    break;
				  case 2:
					  transaction = new Withdrawal(0);
				    break;
				  default:
					  TypeTransactionError = true;
					  JOptionPane.showMessageDialog(
							null,
							"********    Incorrect value, try it again   ********" + "\n\n"
					  );
				}
			} else {
				TypeTransactionError = true;
				JOptionPane.showMessageDialog(
						null,
						"********    Incorrect value, try it again   ********" + "\n\n"
				);
			}	
			
		} while (TypeTransactionError);
		
		if (!Objects.isNull(transaction)) {
			boolean error = false;
			String clientAmount;
			do { 
				clientAmount = JOptionPane.showInputDialog(
					null,
					"********    Insert the amount  ********" + "\n\n"
					+ "Note: You can enter 0 if you want to exit"
				);
				
				if (Objects.isNull(clientAmount)) {
					error = true;
				} else if (clientAmount == "") {
					error = true;
				} else if (clientAmount.length() == 1 && Integer.parseInt(clientAmount) == 0) {
					JOptionPane.showMessageDialog(
							null,
							"********    Thanks for use our system!   ********" + "\n\n"
					 );
					 System.exit(0);
				} else  {
					
					try {
						if (transaction instanceof Withdrawal) {
							if (Integer.parseInt(clientAmount) > account.getLimit()) {
								JOptionPane.showMessageDialog(
										null,
										"********    The amount exceeds your withdrawal limit   ********" + "\n\n"
								);
								error = true;
							} else if (Integer.parseInt(clientAmount) > getMoney()) {
								JOptionPane.showMessageDialog(
									null,
									"********    Sorry, this ATM does not have the amount you want to withdraw   ********" + "\n\n"
								);
								error = true;
							}
						} else if (transaction instanceof Deposit) {}
						
						transaction.setAmount(Integer.parseInt(clientAmount));
					} catch (Exception e) {
						error = true;
						JOptionPane.showMessageDialog(
								null,
								"********    An error has occurred, please enter correct values    ********" + "\n\n"
							);
					}
					
				}
				
			} while(error);
			
			boolean executeError;
			do {
				
				executeError = false;
				String execute = JOptionPane.showInputDialog(
					null,
					"********    Confirm the operation  ********" + "\n\n"
					+ "1- Yes" + "\n\n"
					+ "2- No" + "\n\n"
					+ "Note: You can enter 0 if you want to exit"
				);
				
				if (!Objects.isNull(execute)) {
					
					switch(Integer.parseInt(execute)) {
					  case 0:
						  JOptionPane.showMessageDialog(
								null,
								"********    Thanks for use our system!   ********" + "\n\n"
						  );
						  System.exit(0);
					    break;
					  case 1:
						  try {
							  transaction.execute(account);
							  JOptionPane.showMessageDialog(
									null,
									"********   The operation was successful   ********" + "\n\n"
									+ "Your current balance: " + account.getFormattedMoney() + "\n\n"
							  );
						  } catch (Exception e) {
							  return null;
						  }
					    break;
					  case 2:
						  JOptionPane.showMessageDialog(
								null,
								"********    We have canceled the operation   ********" + "\n\n"
						  );
					    break;
					    
					  default:
						  JOptionPane.showMessageDialog(
								null,
								"********    Incorrect value, try it again   ********" + "\n\n"
						  );
						  executeError = true;
					}			
				} else {
					executeError = true;
				}
			} while(executeError);
		}
		
		return transaction;
	}
	
	public boolean getDoYouWantToBeAnotherTransactionScreen() {
		boolean anotherTransaction = false;
		
		boolean error;
		do {
			
			error = false;
			String repeatText = JOptionPane.showInputDialog(
					null,
					"********   Dou you want to be another operation?   ********" + "\n\n"
					+ "1- Yes" + "\n\n"
					+ "2- No" + "\n\n"
				);
					
				if (!Objects.isNull(repeatText) && repeatText != "" && repeatText.length() == 1) {	
					switch(Integer.parseInt(repeatText)) {
						 case 1:
							  anotherTransaction = true;
						   break;

						 case 2:
							  JOptionPane.showMessageDialog(
									null,
									"********    Thanks for use our system!   ********" + "\n\n"
							  );
							  System.exit(0);
						 break;
						    
						 default:
							 error = true;
							  JOptionPane.showMessageDialog(
								null,
								"********    Incorrect value, try it again   ********" + "\n\n"
							  );
					}	
				} else {
					JOptionPane.showMessageDialog(
						null,
						"********    Please enter a correct value    ********" + "\n\n"
				    );
					error = true;
				}
		} while (error);
		
		return anotherTransaction;
	}
}
