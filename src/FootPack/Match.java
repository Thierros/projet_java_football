package FootPack;


import java.io.Serializable;
import java.util.Random;

public class Match implements Serializable {
    private Equipe equipe1;
    private Equipe equipe2;
    private int scoreEquipe1;
    private int scoreEquipe2;
    private boolean estJoue;

    // Constructeur
    public Match(Equipe equipe1, Equipe equipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.scoreEquipe1 = 0;
        this.scoreEquipe2 = 0;
        this.estJoue = false;
    }

    // Méthode pour jouer le match et générer des scores aléatoires
    public void jouerMatch() {
        Random rand = new Random();
        this.scoreEquipe1 = rand.nextInt(5) + 1; // Score aléatoire entre 1 et 5 pour équipe1
        this.scoreEquipe2 = rand.nextInt(5) + 1; // Score aléatoire entre 1 et 5 pour équipe2
        this.estJoue = true;

        // Mettre à jour les statistiques des équipes
        this.equipe1.addNbButsMarques(this.scoreEquipe1);
        this.equipe1.addNbButsEncaisses(this.scoreEquipe2);
        this.equipe2.addNbButsMarques(this.scoreEquipe2);
        this.equipe2.addNbButsEncaisses(this.scoreEquipe1);

        if (this.scoreEquipe1 > this.scoreEquipe2) {
            this.equipe1.addNbVictoires();
            this.equipe1.addNbPoints(3);
            this.equipe2.addNbDefaites();
        } else if (this.scoreEquipe1 < this.scoreEquipe2) {
            this.equipe2.addNbVictoires();
            this.equipe2.addNbPoints(3);
            this.equipe1.addNbDefaites();
        } else {
            this.equipe1.addNbNuls();
            this.equipe2.addNbNuls();
            this.equipe1.addNbPoints(1);
            this.equipe2.addNbPoints(1);
        }
    }

    // Getters et Setters
    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public int getScoreEquipe1() {
        return scoreEquipe1;
    }

    public void setScoreEquipe1(int scoreEquipe1) {
        this.scoreEquipe1 = scoreEquipe1;
    }

    public int getScoreEquipe2() {
        return scoreEquipe2;
    }

    public void setScoreEquipe2(int scoreEquipe2) {
        this.scoreEquipe2 = scoreEquipe2;
    }

    public boolean isPlayed() {
        return estJoue;
    }

    public void setPlayed(boolean played) {
        estJoue = played;
    }

    @Override
    public String toString() {
        if (estJoue) {
            return equipe1.getNomEquipe() + " " + scoreEquipe1 + " - " + scoreEquipe2 + " " + equipe2.getNomEquipe();
        } else {
            return equipe1.getNomEquipe() + " vs " + equipe2.getNomEquipe() + " (Match non joué)";
        }
    }
}
