import java.util.ArrayList;
import java.util.List;

public class Remplissage {
    Salle salle;
    int nbRangeeUtilise, sommeDist;
    float tauxRemplissage;
    List<RemplissageGroupeRangee> data;
//  attribut ajouté car pratique n'est pas dans l'UML
    int nb_util_tot;


    public Remplissage(Salle salle){
        this.data = new ArrayList<RemplissageGroupeRangee>();
        this.salle = new Salle(salle);
        sommeDist = 0;
        nbRangeeUtilise = 1;
        nb_util_tot = 0;

        // this.algoNaive(1);
        this.AlgoEnum();
        
        tauxRemplissage = (float)nb_util_tot/(float)salle.nb_place_tot;
    }
    
    public Remplissage(Salle salle, int demarrage){
        this.data = new ArrayList<RemplissageGroupeRangee>();
        this.salle = new Salle(salle);
        sommeDist = 0;
        nbRangeeUtilise = 1;
        nb_util_tot = 0;
        
        this.algoNaive(demarrage);
        
        tauxRemplissage = (float)nb_util_tot/(float)salle.nb_place_tot;
    }

    //ici le parametre demarrage est la rangee a la quelle on commance a remplire;
    public void algoNaive(int demarrage){
        int j = 0, k = demarrage;
        int decal = 0;
        List<Integer> gs = new ArrayList<Integer>();
        int nbutil = 0;
        // tant quil reste des reservation et des rangee de libre
        while(!salle.reservations.isEmpty() &&  k < salle.rangees.size()){
            //si on est arrivee a la fin d une rangee on repart au debut de la prochaine
            if(j == salle.rangees.get(k).size()){
                j = 0;
                k = k + salle.P + 1;
                nbRangeeUtilise++;
            }
            // si il y a de la place pour un groupe on le met
            else if (RestePlace(salle.reservations.get(0).nombreSpectateur, decal, salle.rangees.get(k).get(j).capacitee)) {
                salle.rangees.get(k).get(j).capacitee -= salle.reservations.get(0).nombreSpectateur + decal*salle.Q;
                nbutil += salle.reservations.get(0).nombreSpectateur;
                gs.add(salle.reservations.get(0).numGroupeSpectateur);
                salle.reservations.remove(0);
                decal = 1;
                
                if(salle.reservations.isEmpty()){
                    j++;
                    data.add(new RemplissageGroupeRangee(j, k, nbutil, gs));
                    gs.clear();
                    nb_util_tot += nbutil;
                    nbutil = 0;
                }
            }
            //sinon on ajoute la data et on passe au prochain groupe de la rangee actuelle
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

    public boolean RestePlace(int reservation, int decal, int capacitee){
        return (reservation + decal*salle.Q <= capacitee);
    }

    public void AlgoEnum(){
        // on minimise le nombre de range ou les distance de la scene
        // int min = 5000;
        // ou on maximise le taux de remplissage
        float max = 0.0f ;
        Remplissage rtemp = null;
        //on essaye de commencer par des rangee differente et regarder le quelle est optimal;
        for (int i = 1; i <= salle.P+1; i++) {
            Remplissage r = new Remplissage(salle, i);

            if (r.tauxRemplissage > max) {
                rtemp = r;
                max = r.tauxRemplissage;
            }
        }

        // on prend la meilleur solution 
        this.salle = new Salle(rtemp.salle);
        this.nbRangeeUtilise = rtemp.nbRangeeUtilise;
        this.nb_util_tot = rtemp.nb_util_tot;
        this.sommeDist = rtemp.sommeDist;
        this.tauxRemplissage = rtemp.tauxRemplissage;
        this.data.clear();
        for (RemplissageGroupeRangee remplissageGroupeRangee : rtemp.data) {
            this.data.add(remplissageGroupeRangee);
        }

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
        if (salle.reservations.isEmpty()) {
            res += "-1\n";
        }
        for (Reservation r : salle.reservations) {
            res += r.numGroupeSpectateur + " ";
        }
        res += "\n";
        return res;
    }
}
