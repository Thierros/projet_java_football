package FootPack;

//import java.util.Random;

public class Journee {
	private int numJournee;
	private Match[] matches;
	//private int nbTotalJournee;
	//private int nbMatchParJournee;
	//private String dateJournee;
	
	// Définition du constructeur
	public Journee(int numJournee, Equipe[] equipes){
		this.numJournee = numJournee;
		this.matches = new Match[equipes.length / 2];
		generateMatches(equipes);
		simulerMatch();
	}
	
    private void generateMatches(Equipe[] equipes) {
        int numberOfTeams = equipes.length;
        Equipe[] tempEquipes = new Equipe[numberOfTeams];
        System.arraycopy(equipes, 0, tempEquipes, 0, numberOfTeams);
        
        int halfSize = numberOfTeams / 2;

        for (int day = 0; day < numberOfTeams - 1; day++) {
            for (int i = 0; i < halfSize; i++) {
                Equipe equipe1 = tempEquipes[i];
                Equipe equipe2 = tempEquipes[numberOfTeams - 1 - i];
                if (equipe1 != null && equipe2 != null) {
                    matches[i] = new Match(equipe1, equipe2);
                }
            }

            // Rotate teams for the next round
            Equipe lastTeam = tempEquipes[numberOfTeams - 1];
            for (int i = numberOfTeams - 1; i > 1; i--) {
                tempEquipes[i] = tempEquipes[i - 1];
            }
            tempEquipes[1] = lastTeam;
        }
    }
    
    public void simulerMatch() {
    	for(Match match: matches) {
    		match.jouerMatch();
    	}
    }
		
	public int getNumJournee() {
		return this.numJournee;
	}
	
	public Match[] getMatches() {
		return this.matches;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Journee ").append(numJournee).append(":\n");
		for (Match match: matches) {
			sb.append(match).append("\n");
		}
		return sb.toString();
	}
	
}
