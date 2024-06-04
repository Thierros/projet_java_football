package FootPack;

import java.io.Serializable;

public class Journee implements Serializable {
	private int numero;
	private Match[] matches;
	private int nbMatches;
	private int indexe;

	public Journee(){
		nbMatches = 0;
	}

	public Journee(int numero) {
		this();
		this.numero = numero;
//		this.matches = new Match[nbMatches];
		indexe = 0;
	}

	public void ajouterMatch(Match match) {
		matches[indexe++] = match;
	}

	public void simulerJournee() {
		for (Match match : matches) {
			if (!match.isPlayed()) {
				match.jouerMatch();
			}
		}
	}

	public void setNbMatches(int nbMatches){
		this.nbMatches = nbMatches;
		this.matches = new Match[nbMatches];
	}
	public int getNbMatches(){
		return nbMatches;
	}

	public Match[] getMatches(){
		return matches;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Journee ").append(numero).append(":\n");
		for (Match match : matches) {
			sb.append(match).append("\n");
		}
		return sb.toString();
	}
}
