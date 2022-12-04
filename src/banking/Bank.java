package banking;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
	
	private String name;
	private ArrayList<ATM> ATMs;

	public Bank(String name) {
		setName(name);
		ATMs = new ArrayList<ATM>();
	}
	
	public Bank(String name, ArrayList<ATM> ATMs) {
		setName(name);
		setATMs(ATMs);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<ATM> getATMs() {
		return ATMs;
	}
	
	private void setATMs(ArrayList<ATM> ATMs) {
		this.ATMs = ATMs;
	}

	public void addATM(ATM ATM) {
		ATMs.add(ATM);
	}
	
	public boolean removeATM(UUID uuid) {
		int index = 0;
		while (index < ATMs.size())
		{
		   if(ATMs.get(index).getUuid() == uuid)
		   {
			  ATMs.remove(index);
			  return true;
		  }
		  else
		  {
		    ++index;
		  }
		}
		return false;
	}

}
