package banking;

import java.util.ArrayList;

public class Storage { 
	
	/* == Properties == */
	private ArrayList<Client> clients;
	
	/* == Singleton Pattern (Lazy Initialization) == */
	private static Storage instance;

    private Storage(){
    	clients = new ArrayList<Client>();
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    /* == Methods == */
    
    public ArrayList<Client> getClients() {
		return this.clients;
    }
    
    public Client getClient(String email) {
    	Client c = null;
    	ArrayList<Client> clients = Storage.getInstance().getClients();
    	for (int i = 0; i < clients.size(); i++) { 
			Client client = clients.get(i);
			if (client.getEmail().equalsIgnoreCase(email)) {
				c = client;
			}
		}
		return c;
    }

	public void addClient(Client c) throws Exception {
		boolean exists = false;
		ArrayList<Client> clients = Storage.getInstance().getClients();
		for (int i = 0; i < clients.size(); i++) { 
			Client client = clients.get(i);
			if (client.getEmail().equalsIgnoreCase(c.getEmail())) {
				exists = true;
			}
		}
		if (exists) {
			throw new Exception("The email already exists");
		} else {
			this.clients.add(c);
		}
	}
	
}
