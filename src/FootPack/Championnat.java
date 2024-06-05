package FootPack;

import com.sun.source.tree.SynchronizedTree;

import java.io.*;
import java.util.*;

public class Championnat  implements Serializable{
    private  Equipe[] equipes;
    private  Journee[] journees;
    private static int nbEquipe;
    private int indexJournee;

    public Championnat() throws IOException {
        chargerEquipe("equipe.txt");
        indexJournee = 0;
        this.journees = new Journee[nbEquipe - 1];
        genererCalendrier();
    }

    public Championnat(String equipeFileName) throws IOException {
        chargerEquipe(equipeFileName);
        indexJournee = 0;
        this.journees = new Journee[nbEquipe - 1];
        genererCalendrier();
    }


    private void genererCalendrier(){
        for (int i=0; i < nbEquipe - 1; i++){
            Journee journee = new Journee(i+1);
            journee.setNbMatches(nbEquipe/2);
            for (int j = 0; j < journee.getNbMatches(); j++){
                int equipeA = (i + j) % (nbEquipe - 1);
                int equipeB = (nbEquipe - 1 - j + i ) % (nbEquipe - 1);
                if (j == 0)
                    equipeB = nbEquipe - 1;
                Match match = new Match(equipes[equipeA], equipes[equipeB]);
                journee.ajouterMatch(match);
            }
            this.journees[i] = journee;
        }
    }

    public void jouerUneJournee(){
        if(indexJournee<= journees.length-1)
            journees[indexJournee++].simulerJournee();
        else
            System.out.println("Le championnat est clos! Renitialiser le championnat.");
    }

    public void simulerChampionnat()
    {
        for (int i = indexJournee; i < journees.length; i++)
            journees[i].simulerJournee();
        indexJournee = journees.length - 1;
    }

    public void afficherClassement(){
        int n = equipes.length;
        boolean flag;
        do {
            flag = false;
            for (int i = 0; i < n - 1; i++) {
                if (equipes[i].getNbPoints() < equipes[i + 1].getNbPoints()) {
                    Equipe tmpEquipe = equipes[i + 1];
                    equipes[i + 1] = equipes[i];
                    equipes[i] = tmpEquipe;
                    flag = true;
                } else if (equipes[i].getNbPoints() == equipes[i + 1].getNbPoints()) {
                    if (equipes[i].getGoalAverage() < equipes[i + 1].getGoalAverage()) {
                        Equipe tmpEquipe = equipes[i + 1];
                        equipes[i + 1] = equipes[i];
                        equipes[i] = tmpEquipe;
                        flag = true;
                    }
                }
            }
            n--;
        } while (flag);

        String format = "%-5s %-15s %-7s %-13s %-13s %-10s %-9s %-5s %-12s%n";
        System.out.printf(format,"Rang", "Nom Equipe", "Points", "But Marques", "But Encaisses", "Victoires", "Defaites", "Nuls", "GoalAverage");
        System.out.printf(format,"____", "_______________", "_______", "_____________", "_____________", "__________", "_________", "_____", "__________");
        int rang = 0;
        for (Equipe equipe : equipes) {
            int butDiff = equipe.getNbButsMarques() - equipe.getNbButsEncaisses();
            String goalAverage = (butDiff > 0) ? "+" + butDiff : String.valueOf(butDiff);
            System.out.printf(format,
                    ++rang,
                    equipe.getNomEquipe(),
                    equipe.getNbPoints(),
                    equipe.getNbButsMarques(),
                    equipe.getNbButsEncaisses(),
                    equipe.getNbVictoires(),
                    equipe.getNbDefaites(),
                    equipe.getNbNuls(),
                    goalAverage

            );
        }
    }


    public void sauvegarderChampionnat(){
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("championnat.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            System.out.println("Championnat sauvegardé avec succès!");
        }catch (IOException e){
            System.out.println("Une erreur liee au fichier! verifier le chemin d'acces...");
//            e.printStackTrace();
//            System.out.println();
        }
    }

    public static Championnat chargerChampionnat(String  nomFichier) {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(nomFichier);
            ois = new ObjectInputStream(fis);
            return (Championnat) ois.readObject();
        } catch (IOException e) {
            System.out.println("Une erreur liee au fichier! verifier le chemin d'acces...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void chargerEquipe(String fichierEquipe) throws IOException{
        FileReader fr = null;
        BufferedReader br = null;
//        try{
            fr = new FileReader(fichierEquipe);
            br = new BufferedReader(fr);

            nbEquipe = 0;
            Equipe [] tmpListeEquipe;

            String res = br.readLine();
            while (res != null) {
                nbEquipe++;
                res = br.readLine();
            }
            br.close();

            fr = new FileReader(fichierEquipe);
            br = new BufferedReader(fr);
            int indexe = 0;
            equipes = new Equipe[nbEquipe];
            String ligne = br.readLine();
            while (ligne != null) {
                Equipe equipe = new Equipe(ligne);
                equipes[indexe++] = equipe;
                ligne = br.readLine();
            }
            br.close();
//        }catch (IOException e) {
//            System.out.println("Une erreur liee au fichier! verifier le chemin d'acces...");
////            e.printStackTrace();
//        }
    }

    public Equipe[] getEquipes() {
        return equipes;
    }

    public Journee[] getJournees() {
        return journees;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Journee journee : journees) {
            sb.append(journee).append("\n");
        }
        return sb.toString();
    }

    public void listerEquipes(){
        int i = 0;
        for (Equipe e: equipes){
            System.out.print((i+1)+"-"+e.getNomEquipe()+";\t");
            i++;
        }
        System.out.println();
    }

    public void statEquipe(int numEquipe){
        Equipe equipe = equipes[numEquipe];
        System.out.println("STATISTIQUE DE L'EQUIPE POUR LA JOURNEE "+getIndexJournee()+" / "+getJournees().length+" :");
        System.out.println("_________________________________________________");
        System.out.println("Nom: "+equipes[numEquipe].getNomEquipe());
        System.out.println("Nombre de points: "+equipes[numEquipe].getNbPoints());
        System.out.println("Nombre de Défaites: "+equipes[numEquipe].getNbDefaites());
        System.out.println("Nombre de Victoirs: "+equipes[numEquipe].getNbVictoires());
        System.out.println("Nombre de matchs Nuls: "+equipes[numEquipe].getNbNuls());
        System.out.println("Nombre de buts marqués: "+equipes[numEquipe].getNbButsMarques());
        System.out.println("Nombre de buts encaissés: "+equipes[numEquipe].getNbButsEncaisses());
    }

    public void infoMatch(int equipe1, int equipe2){
        String nomEquipe1 = equipes[equipe1].getNomEquipe();
        String nomEquipe2 = equipes[equipe2].getNomEquipe();

        for (Journee j: journees){
            for (Match m: j.getMatches()){
                if (
                        (nomEquipe1.equals(m.getEquipe1().getNomEquipe()) || nomEquipe1.equals(m.getEquipe2().getNomEquipe()))
                        &&
                        (nomEquipe2.equals(m.getEquipe1().getNomEquipe()) || nomEquipe2.equals(m.getEquipe2().getNomEquipe()))
                ){

                    System.out.println("======================================");
                    if (m.isPlayed()){
                        System.out.println(m.getEquipe1().getNomEquipe()+": "+m.getScoreEquipe1() +
                                " - " +
                                m.getScoreEquipe2()+" :"+m.getEquipe2().getNomEquipe());
                    }else{
                        System.out.println("le match "+m.getEquipe1().getNomEquipe()+" vs "+m.getEquipe2().getNomEquipe()+" n'est pas encore joué");
                    }
                    System.out.println("======================================");
                    break;
                }

            }
        }
    }

    public void meilleurScore(){
        String nomEquipe = "";
        int score = 0;
        for (Journee j: journees){
            for (Match m: j.getMatches()){
                if (m.getScoreEquipe1() >= m.getScoreEquipe2()){
                    if(m.getScoreEquipe1() >= score){
                        nomEquipe = m.getEquipe1().getNomEquipe();
                        score = m.getScoreEquipe1();
                    }
                }else{
                    if(m.getScoreEquipe2() >= score){
                        nomEquipe = m.getEquipe2().getNomEquipe();
                        score = m.getScoreEquipe2();
                    }
                }
            }
        }
        System.out.println("======================================");
        System.out.println(nomEquipe+": "+score+" buts.");
        System.out.println("======================================");
    }

    public int getIndexJournee(){
        return indexJournee;
    }

//    public void afficherNumJournee(){
//        System.out.println("========================= JOURNEE "+indexJournee+"/ "+journees.length+" =============================");
//
//    }
}
