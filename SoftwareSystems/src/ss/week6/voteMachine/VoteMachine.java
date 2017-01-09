package ss.week6.voteMachine;

import java.util.List;
import java.util.Map;

import ss.week6.voteMachine.gui.*;

public class VoteMachine {

	public static VoteMachine votemachine;
	public PartyList partylist;
	public VoteList votelist;
	public VoteView view;
	
	public static void main(String[] args) {
		votemachine = new VoteMachine();
		votemachine.start();
	}
	
	public void start() {
		partylist = new PartyList();
		votelist = new VoteList();
		this.view = new VoteGUIView(this);
		votelist.addObserver(view);
		partylist.addObserver(view);
		view.start();
	}
	
	public void addParty(String party) {
		partylist.addParty(party);
	}
	
	public void vote(String party) {
		votelist.addVote(party);
	}
	
	public List<String> getParties() {
		return partylist.getParties();
	}
	
	public Map<String, Integer> getVotes() {
		return votelist.getVotes();
	}
}
