import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class getSalleData {
    public Salle readSalleData(String fileName) {
        try {
            File fichier = new File(fileName);
            Scanner sc = new Scanner(fichier);
            
            Salle salle = new Salle();

            int nbGroupe = sc.nextInt();

            List<Integer> nbRangeGroup = new ArrayList<Integer>();

            for (int i = 0; i < nbGroupe; i++) {
                nbRangeGroup.add(sc.nextInt());
            }
            
            for (Integer x : nbRangeGroup) {
                GroupeDeRangees gr = new GroupeDeRangees();
                for (int i = 0; i < x; i++) {
                    int capacitee = sc.nextInt();
                    int distanceDeLaScene = sc.nextInt();
                    Rangees r = new Rangees(capacitee, distanceDeLaScene);
                    gr.addRangees(r);
                }
                salle.addGroupe(gr);
            }
            sc.close();
            return salle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
