package FootPack;

public class Equipe {
    private String nomEquipe;
    private int nbPoints;
    private int nbButsMarques;
    private int nbButsEncaisses;
    private int nbVictoires;
    private int nbDefaites;
    private int nbNuls;

    public Equipe(String nomEquipe){
        this.nomEquipe = nomEquipe;
        this.nbPoints = 0;
        this.nbButsEncaisses = 0;
        this.nbButsMarques = 0;
        this.nbVictoires = 0;
        this.nbDefaites = 0;
        this.nbNuls = 0;
    }

    public void addNbPoints(int nbPoints){
        this.nbPoints += nbPoints;
    }

    public void addNbButsMarques(int nbButsMarques){
        this.nbButsMarques += nbButsMarques;
    }

    public void addNbButsEncaisses(int nbButsEncaisses){
        this.nbButsEncaisses += nbButsEncaisses;
    }

    public void addNbVictoires(){
        this.nbVictoires++;
    }

    public void addNbDefaites(){
        this.nbDefaites++;
    }

    public void addNbNuls(){
        this.nbNuls++;
    }

//    Les getters
    public String getNomEquipe(){
        return this.nomEquipe;
    }

    public int getNbPoints(){
        return this.nbPoints;
    }

    public int getNbButsMarques(){
        return this.nbButsMarques;
    }

    public int getNbButsEncaisses(){
        return this.nbButsEncaisses;
    }

    public int getNbVictoires(){
        return this.nbVictoires;
    }

    public int getNbDefaites(){
        return this.nbDefaites;
    }

    public int getNbNuls(){
        return this.nbNuls;
    }

//    Les setters
    public void setNomEquipe(String nomEquipe){
        this.nomEquipe = nomEquipe;
    }

    public void setNbPoints(int nbPoints){
        this.nbPoints = nbPoints;
    }

    public void setNbButsMarques(int nbButsMarques){
        this.nbButsMarques = nbButsMarques;
    }

    public void setNbButsEncaisses(int nbButsEncaisses) {
        this.nbButsEncaisses = nbButsEncaisses;
    }

    public void setNbVictoires(int nbVictoires) {
        this.nbVictoires = nbVictoires;
    }

    public void setNbDefaites(int nbDefaites) {
        this.nbDefaites = nbDefaites;
    }

    public void setNbNuls(int nbNuls) {
        this.nbNuls = nbNuls;
    }

    public String toString(){
        return nomEquipe + " {Points: " + nbPoints + ", Buts Marques: " + nbButsMarques + ",  Buts Encaisses: " + nbButsEncaisses + ", Victoires: " + nbVictoires;
    }


}
