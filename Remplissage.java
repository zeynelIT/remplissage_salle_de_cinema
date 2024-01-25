import java.util.ArrayList;
import java.util.List;

public class Remplissage {
    Salle salle;
    int nbRangeeUtilise, sommeDist;
    float tauxRemplissage;
    List<RemplissageGroupeRangee> data;


    public Remplissage(Salle salle){
        this.data = new ArrayList<RemplissageGroupeRangee>();
        this.salle = new Salle(salle);
        int decal = 0;
        int i = 0, j = 0, k = 1;
        sommeDist = 0;
        nbRangeeUtilise = 1;
        List<Integer> gs = new ArrayList<Integer>();
        int nbutil = 0;
        int nb_util_tot = 0;
        while(i < salle.reservations.size() && (j < salle.groupes.size() || k <= salle.rangees.length)){
            if(j == salle.rangees[k].size()){
                j = 0;
                k = k + salle.P + 1;
                nbRangeeUtilise++;
            }
            else if (salle.reservations.get(i) + decal*salle.Q <= salle.rangees[k].get(j).capacitee) {
                salle.rangees[k].get(j).capacitee -= salle.reservations.get(i) + decal*salle.Q;
                nbutil += salle.reservations.get(i);
                i++; 
                decal = 1;
                gs.add(i);
                
                if(i == salle.reservations.size()){
                    data.add(new RemplissageGroupeRangee(j, k, nbutil, gs));
                    gs.clear();
                    nb_util_tot += nbutil;
                    nbutil = 0;

                }
            }
            else{
                sommeDist += salle.rangees[k].get(j).distanceDeLaScene;
                j++;
                decal = 0;

                data.add(new RemplissageGroupeRangee(j, k, nbutil, gs));
                gs.clear();
                nb_util_tot += nbutil;
                nbutil = 0;
            }
        }
        tauxRemplissage = (float)nb_util_tot/(float)salle.nb_place_tot;
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
        return res;
    }
}
