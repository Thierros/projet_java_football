import FootPack.Equipe;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Equipe monEquipe = new Equipe("FC Barca");
        System.out.println(monEquipe.toString());
        monEquipe.addNbButsMarques(2);
        monEquipe.addNbPoints(3);
        monEquipe.addNbVictoires();
        monEquipe.setNomEquipe("FC Barcelona");
        System.out.println(monEquipe.toString());
    }
}