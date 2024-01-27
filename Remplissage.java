import java.util.ArrayList;
import java.util.List;

public class Remplissage {
    Salle salle;
    int nbRangeeUtilise, sommeDist;
    float tauxRemplissage;
    List<RemplissageGroupeRangee> data;
    List<Integer> grNonPlace;
//  attribut ajouté car pratique n'est pas dans l'UML
    int nb_util_tot;
    int last_reservation;


    public Remplissage(Salle salle){
        grNonPlace = new ArrayList<Integer>();
        this.data = new ArrayList<RemplissageGroupeRangee>();
        // this.salle = new Salle(salle);
        this.salle = new Salle(salle);
        sommeDist = 0;
        nbRangeeUtilise = 1;
        last_reservation = 0;
        nb_util_tot = 0;

        this.algoNaive();
        
        tauxRemplissage = (float)nb_util_tot/(float)salle.nb_place_tot;
        
        //groupe non placee
        for (int l = last_reservation; l < this.salle.reservations.size(); l++) {
            grNonPlace.add(l);
        }
    }

    public void algoNaive(){
        int j = 0, k = 1;
        int decal = 0;
        List<Integer> gs = new ArrayList<Integer>();
        int nbutil = 0;
        while(last_reservation < salle.reservations.size() && (j < salle.groupes.size() || k <= salle.rangees.size())){
            if(j == salle.rangees.get(k).size()){
                j = 0;
                k = k + salle.P + 1;
                nbRangeeUtilise++;
            }
            else if (salle.reservations.get(last_reservation) + decal*salle.Q <= salle.rangees.get(k).get(j).capacitee) {
                salle.rangees.get(k).get(j).capacitee -= salle.reservations.get(last_reservation) + decal*salle.Q;
                nbutil += salle.reservations.get(last_reservation);
                last_reservation++; 
                decal = 1;
                gs.add(last_reservation);
                
                if(last_reservation == salle.reservations.size()){
                    j++;
                    data.add(new RemplissageGroupeRangee(j, k, nbutil, gs));
                    gs.clear();
                    nb_util_tot += nbutil;
                    nbutil = 0;
                }
            }
            else{
                sommeDist += salle.rangees.get(k).get(j).distanceDeLaScene; // on peut mettre juste k
                j++;
                decal = 0;

                data.add(new RemplissageGroupeRangee(j, k, nbutil, gs));
                gs.clear();
                nb_util_tot += nbutil;
                nbutil = 0;
            }
        }
    }

    public void AlgoEnumTot(){

    }

    public String ToString(){
        String res = "";
        res += nbRangeeUtilise + " " + sommeDist + " " + tauxRemplissage + "\n";
        for (RemplissageGroupeRangee remplissageGroupeRangee : data) {
            res += remplissageGroupeRangee.numGroupe + " " + remplissageGroupeRangee.numRangee;
            for (Integer gs : remplissageGroupeRangee.numGroupeSpectateur) {
                res += " " + gs;
            }
            res += " " + remplissageGroupeRangee.nbPlaceUtilisee + "\n";
        }
        res += "Non places\n";
        if (grNonPlace.isEmpty()) {
            res += "-1\n";
        }
        for (Integer g : grNonPlace) {
            res += g + " ";
        }
        res += "\n";
        return res;
    }
}
