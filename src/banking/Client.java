package banking;

import java.util.ArrayList;
import java.util.UUID;

public class Client {

	private UUID uuid;
	private String name;
	private String email;
	
	private ArrayList<Account> accounts;
	
	public Client(String name, String email) throws Exception {
		setUuid(UUID.randomUUID());
		setEmail(email);
		setName(name);
		accounts = new ArrayList<Account>();
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if (Util.patternMatches(email, "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			this.email = email;
		} else {
			throw new Exception("Invalid Email");
		}
	}
	
}
