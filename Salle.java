import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Salle {
    List<Rangees> [] rangees; 
    List<GroupeDeRangees> groupes;
    int P, K, Q; 
    List<Integer> reservations;
    int nb_place_tot;

    public Salle(){
        groupes = new ArrayList<GroupeDeRangees>();
        P = 0;
        K = 0;
        Q = 0;
        reservations = new ArrayList<Integer>();
    }

    public Salle(List<GroupeDeRangees> groupes){
        this.groupes = new ArrayList<GroupeDeRangees>(groupes);
        P = 0;
        K = 0;
        Q = 0;
        reservations = new ArrayList<Integer>();     
    }

    public Salle(List<GroupeDeRangees> groupes, int P, int K, int Q){
        this.groupes = new ArrayList<GroupeDeRangees>(groupes);
        this.P = P;
        this.K = K;
        this.Q = Q;
        reservations = new ArrayList<Integer>();
    }


    public Salle(List<GroupeDeRangees> groupes, int P, int K, int Q, List<Integer> reservations){
        this.groupes = new ArrayList<GroupeDeRangees>(groupes);
        this.P = P;
        this.K = K;
        this.Q = Q;
        this.reservations = new ArrayList<Integer>(reservations);
    }

    public Salle(Salle salle){
        this.groupes = new ArrayList<GroupeDeRangees>(salle.groupes);
        this.P = salle.P;
        this.K = salle.K;
        this.Q = salle.Q;
        this.reservations = new ArrayList<Integer>(salle.reservations);
    }

    public void addGroupe(GroupeDeRangees groupe){
        groupes.add(new GroupeDeRangees(groupe.rangees));
    }

    public void readSalleData(String fileName) {
        try {
            int maxRange = 0;
            nb_place_tot = 0;
            File fichier = new File(fileName);
            Scanner sc = new Scanner(fichier);
            
            int nbGroupe = sc.nextInt();

            List<Integer> nbRangeGroup = new ArrayList<Integer>();

            for (int i = 0; i < nbGroupe; i++) {
                nbRangeGroup.add(sc.nextInt());
            }
            
            for (Integer x : nbRangeGroup) {
                GroupeDeRangees gr = new GroupeDeRangees();
                for (int i = 0; i < x; i++) {
                    int capacitee = sc.nextInt();
                    nb_place_tot += capacitee;
                    int distanceDeLaScene = sc.nextInt();
                    if (distanceDeLaScene > maxRange)
                        maxRange = distanceDeLaScene;
                    Rangees r = new Rangees(capacitee, distanceDeLaScene);
                    gr.addRangees(r);
                }
                this.addGroupe(gr);
            }

            /*on initialise les rangees */
            rangees = new List[maxRange+1];
            for (int i = 0; i <= maxRange; i++) {
                rangees[i] = new ArrayList<Rangees>();
            }

            /*on rempli les rangees*/
            for (GroupeDeRangees gr : this.groupes) {
                for (Rangees r : gr.rangees) {
                    rangees[r.distanceDeLaScene].add(r);
                }                
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readConstraintData(String fileName){
        try {
            File fichier = new File(fileName);
            Scanner sc = new Scanner(fichier);
            P = sc.nextInt();
            K = sc.nextInt();
            Q = sc.nextInt();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readReservationData(String fileName){
        try {
            File fichier = new File(fileName);
            Scanner sc = new Scanner(fichier);
            int nbRes = sc.nextInt();
            for (int i = 0; i < nbRes; i++) {
                reservations.add(sc.nextInt());
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String fillSalle(){
        String res = "";
        return res;
    }

    public String ToString(){
        String res = "";
        for (int i = 0; i < groupes.size(); i++) {
            GroupeDeRangees gr = groupes.get(i);
            res = res + "groupe " + (i+1) + ":\n";
            for (int j = 0; j < gr.rangees.size(); j++) {
                res = res + "rangee " + gr.rangees.get(j).distanceDeLaScene + " capacitee " + gr.rangees.get(j).capacitee + "\n";
            }
            res = res + "\n";
        }
        res = res + "P : " + P + "\n";
        res = res + "K : " + K + "\n";
        res = res + "Q : " + Q + "\n\n";

        res = res + "Reservation : \n";
        for (Integer reservation : reservations) {
            res = res + reservation + " ";
        }
        res = res + "\n";
        return res;
    }
}
