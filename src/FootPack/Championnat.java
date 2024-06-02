package FootPack;

import java.io.*;
import java.util.*;

public class Championnat {
    private List<Equipe> equipes;
    private List<Journee> journees;

    Championnat(List<Equipe> equipes){
        if(equipes.size() < 4 || equipes.size() > 10)
            throw new IllegalArgumentException("le nombre d'equipes requis est paire et compris entre 4 et 10 !!!");

        this.equipes = equipes;
        this.journees = new ArrayList<>();
    }


    private void genererCalendrier(){
        int nbEquipes = equipes.size();

        for (int i=0; i < nbEquipes - 1; i++){
            Journee journee = new Journee(i+1, equipes.toArray(new Equipe[0]));
            for (int j = 0; j < nbEquipes / 2; j++){
                int equipeA = (i + j) % (nbEquipes - 1);
                int equipeB = (nbEquipes -1 -j +i ) % (nbEquipes - 1);
                if (j == 0)
                    equipeB = 0;
                Match match = new Match(equipes.get(equipeA), equipes.get(equipeB));
//                journee.ajouterMatch(match);
            }
            this.journees.add(journee);
        }
    }

    public void simulerChampionnat()
    {
//        for (Journee journee:this.journees)
//            journee.simulerJournee();
    }

    public void afficherClassement(){
        Collections.sort(equipes, new Comparator<Equipe>(){
            public int compare(Equipe e1, Equipe e2){
                if(e1.getNbPoints() != e2.getNbPoints()){
                    return e1.getNbPoints() - e2.getNbPoints();
                }else{
                    return e1.getGoalAverage() - e2.getGoalAverage();
                }
            }
        });

        System.out.println("Classement du championnat");
        for(int i=0; i< this.equipes.size(); i++){
            System.out.println((i+1) + "." + this.equipes.get(i));
        }
    }

    public void sauvegarderChampionnat(String nomFichier){
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(nomFichier);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        }catch (IOException e){

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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public List<Journee> getJournees() {
        return journees;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Journee journee : journees) {
            sb.append(journee).append("\n");
        }
        return sb.toString();
    }
}
