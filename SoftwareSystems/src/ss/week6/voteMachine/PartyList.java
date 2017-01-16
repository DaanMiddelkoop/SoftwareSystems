package ss.week6.voteMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PartyList extends Observable {

	//store all parties in a list
	//adding a party
	//retrieve all parties in a list
	
	private List<String> parties = new ArrayList<String>();
	
	public void addParty(String partyName) {
		parties.add(partyName);
		setChanged();
		notifyObservers("party");
	}
	
	public List<String> getParties() {
		return parties;
	}
	
	public boolean hasParty(String party) {
		return parties.contains(party);
	}
	
	
}
