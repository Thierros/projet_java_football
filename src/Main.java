import FootPack.Championnat;
import FootPack.Equipe;
import FootPack.Journee;

import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
//        declaration objets globaux
        Scanner sc = new Scanner(System.in);

        Championnat championnat = Championnat.chargerChampionnat("data/championnat.dat");
        System.out.println("Journee: "+championnat.getIndexJournee());
        if(args.length <=1 ){};

        while(true){
            System.out.println("***************************** M E N U - P R I N C I P AL *****************************:");
            System.out.println("1. Charger une nouvelle liste d'equipe");
            System.out.println("2. Afficher le programme d'une journee");
            System.out.println("3. Afficher le programme complet du championnat");
            System.out.println("4. Afficher la liste des equipes");
            System.out.println("5. Afficher le classement du chanpionnat");
            System.out.println("6. Afficher les statistiques d'une equipe");
            System.out.println("7. Afficher le resultat d'un match");
            System.out.println("8. Afficher le meilleur score");
            System.out.println("9. Jouer une Journee du championnat");
            System.out.println("10. Jouer toutes les journees du championnat");
            System.out.println("11. Sauvegarder le championnat");
            System.out.println("12. Renitialiser le championnat");
            System.out.println("13. Quitter");
            System.out.print("Choisissez une option: ");
            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.println();
                    System.out.println("________________________1: CHARGEMENT DES EQUIPES ________________________" );
                    System.out.print("Entrez le chemin complet du fichier de la liste des equipes: ");
                    String filename = sc.nextLine();
                    try{
                        championnat = new Championnat(filename);
                    }catch (IOException e){
                        System.out.println("Une erreur liee a votre fichier. Une liste par defaut d'equipe seras chargée:");
                        try{
                            championnat = new Championnat();
                        }catch (IOException ex){};
                    };

                    System.out.println();
                    break;
                case 2:
                    System.out.print("Numero de la  journnee (entre 1 et "+ championnat.getJournees().length +"): ");
                    int numJournee = sc.nextInt();
                    System.out.println();
                    System.out.println("________________________2: PROGRAMME DE LA JOURNEE " + (numJournee) + " ________________________" );
                    System.out.println(championnat.getJournees()[numJournee-1].toString());
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("________________________3: PROGRAMME COMPLET DU CHAMPIONNAT ________________________" );
                    assert championnat != null;
                    for(Journee j: championnat.getJournees()){
                        System.out.println(j.toString());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("________________________4: LISTE DES EQUIPES ________________________" );
                    assert championnat != null;
                    for(Equipe equipe: championnat.getEquipes()){
                        System.out.println(equipe.getNomEquipe());
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("___________________________________5: CLASSEMENT DU CHAMPIONNAT: JOURNEE "+championnat.getIndexJournee()+" / "+championnat.getJournees().length+"________________________" );
                    championnat.afficherClassement();
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("________________________6: STATISTIQUES D'UNE EQUIPE ________________________" );
                    championnat.listerEquipes();
                    System.out.print("Numero de l'equipe (entre 1 et "+ championnat.getEquipes().length +"): ");
                    int numEquipe = sc.nextInt();
                    System.out.println();
                    championnat.statEquipe(numEquipe-1);
                    System.out.println();
                    break;
                case 7:
                    System.out.println();
                    System.out.println("________________________7: RESULTAT MATCH ________________________" );
                    championnat.listerEquipes();
                    System.out.print("Numero de l'equipe 1: (entre 1 et "+ championnat.getEquipes().length +"): ");
                    int equipe1 = sc.nextInt();
                    System.out.print("Numero de l'equipe 2: (entre 1 et "+ championnat.getEquipes().length +"): ");
                    int equipe2 = sc.nextInt();
                    championnat.infoMatch(equipe1-1, equipe2-1);
                    System.out.println();
                    break;
                case 8:
                    System.out.println();
                    System.out.println("________________________8: MEILLEUR SCORE ________________________" );
                    championnat.meilleurScore();
                    System.out.println();
                    break;
                case 9:
                    System.out.println();
                    System.out.println("________________________9: JOUER UNE JOURNEE ________________________" );
                    championnat.jouerUneJournee();
                    System.out.println();
                    break;
                case 10:
                    System.out.println();
                    System.out.println("________________________10: JOUER TOUTES LES JOURNEES ________________________" );
                    championnat.simulerChampionnat();
                    System.out.println();
                    break;
                case 11:
                    System.out.println();
                    System.out.println("________________________11: SAUVEGARDER LE CHAMPIONNAT ________________________" );
                    championnat.sauvegarderChampionnat();
                    System.out.println();
                    break;
                case 12:
                    System.out.println();
                    System.out.println("________________________12: RENITIALISER LE CHAMPIONNAT ________________________" );
                    try{
                        championnat = new Championnat();
                    }catch (IOException ex){};
                    System.out.println("Le championnat a eté  renialiser avec succès. N'oubliez pas de sauvegarder!");
                    System.out.println();
                    break;
                case 13:
                    System.out.println();
                    System.out.println("Au revoir !");
                    System.out.println("©Copyrigth IDSI/INPHB 2024, by Thierry - Pekanri - Adams - Catherine");
                    sc.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }

    }
}