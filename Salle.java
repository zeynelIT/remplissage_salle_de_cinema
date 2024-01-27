import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Salle {
    @SuppressWarnings("unchecked")
    ArrayList<List<Rangees>> rangees; 
    List<GroupeDeRangees> groupes;
    int P, K, Q; 
    List<Reservation> reservations;
    int nb_place_tot;

    public Salle(){
        groupes = new ArrayList<GroupeDeRangees>();
        P = 0;
        K = 0;
        Q = 0;
        reservations = new ArrayList<Reservation>();
    }

    public Salle(List<GroupeDeRangees> groupes){
        this.groupes = new ArrayList<GroupeDeRangees>(groupes);
        P = 0;
        K = 0;
        Q = 0;
        reservations = new ArrayList<Reservation>();     
    }

    public Salle(List<GroupeDeRangees> groupes, int P, int K, int Q){
        this.groupes = new ArrayList<GroupeDeRangees>(groupes);
        this.P = P;
        this.K = K;
        this.Q = Q;
        reservations = new ArrayList<Reservation>();
    }


    public Salle(List<GroupeDeRangees> groupes, int P, int K, int Q, List<Reservation> reservations){
        this.groupes = new ArrayList<GroupeDeRangees>(groupes);
        this.P = P;
        this.K = K;
        this.Q = Q;
        this.reservations = new ArrayList<Reservation>();
        for (Reservation reservation : reservations) {
            this.reservations.add(new Reservation(reservation));
        }
    }

    public Salle(Salle salle){
        this.groupes = new ArrayList<GroupeDeRangees>();
        for (GroupeDeRangees gr : salle.groupes) {
            groupes.add(new GroupeDeRangees(gr));
        }
        this.P = salle.P;
        this.K = salle.K;
        this.Q = salle.Q;
        this.reservations = new ArrayList<Reservation>();
        this.nb_place_tot = salle.nb_place_tot;
        
        for (Reservation reservation : salle.reservations) {
            this.reservations.add(new Reservation(reservation));
        }

        rangees = new ArrayList<List<Rangees>>();
        for (int i = 0; i < salle.rangees.size(); i++) {
            rangees.add(new ArrayList<Rangees>());
        }

        /*on rempli les rangees*/
        for (GroupeDeRangees gr : this.groupes) {
            for (Rangees r : gr.rangees) {
                rangees.get(r.distanceDeLaScene).add(r);
            }                
        }
    }

    public void addGroupe(GroupeDeRangees groupe){
        groupes.add(new GroupeDeRangees(groupe));
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
            rangees = new ArrayList<List<Rangees>>();
            for (int i = 0; i <= maxRange; i++) {
                rangees.add(new ArrayList<Rangees>());
            }

            /*on rempli les rangees*/
            for (GroupeDeRangees gr : this.groupes) {
                for (Rangees r : gr.rangees) {
                    rangees.get(r.distanceDeLaScene).add(r);
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
                reservations.add(new Reservation(sc.nextInt(), i+1));
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        for (Reservation reservation : reservations) {
            res = res + reservation.nombreSpectateur + " ";
        }
        res = res + "\n";
        return res;
    }
}
