import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Checker2 {
    /*description de la salle */
    int groupeDeRangeeCapacitee[][];
    int groupeDeRangeeDistance[][];

    /*description des contrainte */
    int P,Q,K;

    /*description des reservation */
    int reservation[];

    public Checker2(){
        reservation = new int[100];
        for (int i = 0; i < reservation.length; i++) {
            reservation[i] = -1;
        }
        groupeDeRangeeDistance = new int[100][100];
        groupeDeRangeeCapacitee = new int[100][100];
        for (int i = 0; i < groupeDeRangeeCapacitee.length; i++) {
            for (int j = 0; j < groupeDeRangeeCapacitee[0].length; j++) {
                groupeDeRangeeCapacitee[i][j] = -1;
                groupeDeRangeeDistance[i][j] = -1;
            }
        }
        P = 0;
        Q = 0;
        K = 0;
    }

    public void readSalleData(String fileName) {
        try {
            File fichier = new File(fileName);
            Scanner sc = new Scanner(fichier);
            
            int nbGroupe = sc.nextInt();

            List<Integer> nbRangeGroup = new ArrayList<Integer>();

            for (int i = 0; i < nbGroupe; i++) {
                nbRangeGroup.add(sc.nextInt());
            }
            int g = 1;
            for (Integer x : nbRangeGroup) {
                for (int i = 1; i <= x; i++) {
                    int capacitee = sc.nextInt();
                    groupeDeRangeeCapacitee[g][i] = capacitee;
                    int distanceDeLaScene = sc.nextInt();
                    groupeDeRangeeDistance[g][i] = distanceDeLaScene;
                }
                g++;
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
            for (int i = 1; i <= nbRes; i++) {
                reservation[i] = sc.nextInt();
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean Check(String fileName){
        try {
            File fichier = new File(fileName);
            Scanner sc = new Scanner(fichier);

            // int nbRangs = sc.nextInt();
            // int sommeDistances = sc.nextInt();
            // float tauxRemplissage = sc.nextFloat();
            sc.nextLine();

            /******************** */
            List<Integer> rangeeUtilise = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("Non places")) {
                    break;
                }
                String[] entiers = line.split(" ");
                int[] tableauEntiers = new int[entiers.length];
                for (int i = 0; i < entiers.length; i++) {
                    tableauEntiers[i] = Integer.parseInt(entiers[i]);
                }
                int i = 0;
                int numGroupe = tableauEntiers[i];
                i++;
                int numRangee = tableauEntiers[i];
                i++;
                int numRes[] = new int[entiers.length - 3];
                while (i < tableauEntiers.length-1) {
                    numRes[i-2] = tableauEntiers[i];
                    i++;
                }
                int nbPlaceUtil = tableauEntiers[i];

                /************on check***********/
                int j = 0;
                int espace_requis = reservation[numRes[j]];
                for (j = 1; j < numRes.length; j++) {
                    espace_requis += Q;
                    espace_requis += reservation[numRes[j]];
                }

                if (groupeDeRangeeCapacitee[numGroupe][numRangee] < espace_requis) {
                    return false;
                }

                for (int k : rangeeUtilise) {
                    if (!(groupeDeRangeeDistance[numGroupe][numRangee] < k-P || k+P < groupeDeRangeeDistance[numGroupe][numRangee]) && !(groupeDeRangeeDistance[numGroupe][numRangee] == k)) {
                        return false;
                    }
                }
                if (!rangeeUtilise.contains(groupeDeRangeeDistance[numGroupe][numRangee])) {
                    rangeeUtilise.add(groupeDeRangeeDistance[numGroupe][numRangee]);
                }
                /*******************************/
            }
            /********************** */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public String ToString(){
        String res = "";
        res += "groupe de rangee:\n";
        int groupeRange = 1;
        int numRangee = 1;
        while(groupeDeRangeeCapacitee[groupeRange][numRangee] != -1){
            numRangee = 1;
            res += "groupe " + groupeRange + " :\n";
            while(groupeDeRangeeCapacitee[groupeRange][numRangee] != -1){
                res += "rangee " + numRangee + " : " + groupeDeRangeeCapacitee[groupeRange][numRangee] + " " + groupeDeRangeeDistance[groupeRange][numRangee] + "\n";
                numRangee++;
            }
            groupeRange++;
        }
        res += "contraint : " + P + K + Q + "\n";
        int numRes = 1;
        res += "reservation : \n";
        while (reservation[numRes] != -1) {
           res += numRes + " : " + reservation[numRes] + "\n"; 
           numRes++;
        }
        return res;
    }
}
