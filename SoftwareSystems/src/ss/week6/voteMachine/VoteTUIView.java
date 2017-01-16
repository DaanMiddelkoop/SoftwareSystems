package ss.week6.voteMachine;

import java.util.*;

public class VoteTUIView implements VoteView {

	public VoteMachine control;
	
	public VoteTUIView(VoteMachine control) {
		this.control = control;
	}
	
	public void start() {
		System.out.println("input: ");
		Scanner scan = new Scanner(System.in);
		String line = null;
		while ((line = scan.nextLine()) != null) {
			if (line.equals("VOTE")) {
				System.out.println("Gimme a party, bitch!");
				String party = scan.nextLine();
				control.vote(party);
			} else if (line.equals("ADD PARTY")) {
				System.out.println("What party do you want to add?");
				String addparty = scan.nextLine();
				control.addParty(addparty);
			} else if (line.equals("VOTES")) {
				showVotes(control.votelist.getVotes());
			} else if (line.equals("PARTIES")) {
				showParties(control.partylist.getParties());
			} else if (line.equals("EXIT")) {
				break;
			} else if (line.equals("HELP")) {
				//?
			} else {
				showError("THAT'S NOT A VALID COMMAND BITCH");
			}
		}
		scan.close();
	}
	
	public void showVotes(Map<String, Integer> votes) {
		System.out.println("Votes: ");
		Set<String> keyset = votes.keySet();
		for (String key : keyset) {
			System.out.println("Party " + key + " has " + votes.get(key) + " votes.");
		}
	}
	
	public void showParties(List<String> parties) {
		System.out.println("Parties: ");
		for (String party : parties) {
			System.out.println(party);
		}
	}
	
	//?
	public void showError(String error) {
		System.out.println(error);
	}
	
	public void update(Observable o, Object arg) {
		if (arg.equals("vote")) {
			System.out.println("Voted have you.");
		} else if (arg.equals("party")) {
			System.out.println("Added a party you have.");
		} else {
			System.out.println("Weird things are happening...");
		}
	}
	
}
