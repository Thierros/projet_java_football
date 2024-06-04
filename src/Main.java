import FootPack.Championnat;
import FootPack.Equipe;
import FootPack.Journee;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        declaration objets globaux
        Scanner sc = new Scanner(System.in);
//        charger les donnees du championnat
        Championnat championnat = Championnat.chargerChampionnat("data/championnat.dat");


        while(true){
            System.out.println("***************************** M E N U - P R I N C I P AL *****************************:");
            System.out.println("1. Afficher le programme d'une journee");
            System.out.println("2. Afficher le programme complet du championnat");
            System.out.println("3. Afficher la liste des equipes");
            System.out.println("4. Afficher le classement du chanpionnat");
            System.out.println("5. Afficher les statistiques d'une equipe");
            System.out.println("6. Afficher le resultat d'un match");
            System.out.println("7. Afficher le meilleur score");
            System.out.println("8. Jouer une Journee du championnat");
            System.out.println("9. Jouer toutes les journees du championnat");
            System.out.println("10. Sauvegarder le championnat");
            System.out.println("11. Renitialiser le championnat");
            System.out.println("12. Quitter");
            System.out.print("Choisissez une option: ");
            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Numero de la  journnee (entre 1 et "+ championnat.getJournees().length +"): ");
                    int numJournee = sc.nextInt();
                    System.out.println();
                    System.out.println("________________________1: PROGRAMME DE LA JOURNEE " + (numJournee) + " ________________________" );
                    System.out.println(championnat.getJournees()[numJournee-1].toString());
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("________________________2: PROGRAMME COMPLET DU CHAMPIONNAT ________________________" );
                    for(Journee j: championnat.getJournees()){
                        System.out.println(j.toString());
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("________________________3: LISTE DES EQUIPES ________________________" );
                    for(Equipe equipe: championnat.getEquipes()){
                        System.out.println(equipe.getNomEquipe());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("________________________4: CLASSEMENT DU CHAMPIONNAT ________________________" );
                    championnat.afficherClassement();
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("________________________5: STATISTIQUES D'UNE EQUIPE ________________________" );
                    championnat.listerEquipes();
                    System.out.print("Numero de l'equipe (entre 1 et "+ championnat.getEquipes().length +"): ");
                    int numEquipe = sc.nextInt();
                    System.out.println();
                    championnat.statEquipe(numEquipe-1);
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("________________________6: RESULTAT MATCH ________________________" );
                    championnat.listerEquipes();
                    System.out.print("Numero de l'equipe 1: (entre 1 et "+ championnat.getEquipes().length +"): ");
                    int equipe1 = sc.nextInt();
                    System.out.print("Numero de l'equipe 2: (entre 1 et "+ championnat.getEquipes().length +"): ");
                    int equipe2 = sc.nextInt();
                    championnat.infoMatch(equipe1-1, equipe2-1);
                    System.out.println();
                    break;
                case 7:
                    System.out.println();
                    System.out.println("________________________7: MEILLEUR SCORE ________________________" );
                    championnat.meilleurScore();
                    System.out.println();
                    break;
                case 8:
                    System.out.println();
                    System.out.println("________________________8: JOUER UNE JOURNEE ________________________" );
                    championnat.jouerUneJournee();
                    System.out.println();
                    break;
                case 9:
                    System.out.println();
                    System.out.println("________________________9: JOUER TOUTES LES JOURNEES ________________________" );
                    championnat.simulerChampionnat();
                    System.out.println();
                    break;
                case 10:
                    System.out.println();
                    System.out.println("________________________10: SAUVEGARDER LE CHAMPIONNAT ________________________" );
                    championnat.sauvegarderChampionnat();
                    System.out.println();
                    break;
                case 11:
                    System.out.println();
                    System.out.println("________________________11: RENITIALISER LE CHAMPIONNAT ________________________" );
//                    championnat.meilleurScore();
                    System.out.println();
                    break;
                case 12:
                    System.out.println("Au revoir !");
                    sc.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }

    }
}