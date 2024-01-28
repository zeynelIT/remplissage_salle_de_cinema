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
        nbRangeeUtilise = 0;
        nb_util_tot = 0;

        // this.algoNaive(1);
        this.AlgoEnum(1);
        
        tauxRemplissage = (float)nb_util_tot/(float)salle.nb_place_tot;
    }
    
    public Remplissage(Remplissage r){
        this.data = new ArrayList<RemplissageGroupeRangee>();
        for (RemplissageGroupeRangee rgr : r.data) {
            this.data.add(new RemplissageGroupeRangee(rgr));
        }
        this.salle = new Salle(r.salle);
        sommeDist = r.sommeDist;
        nbRangeeUtilise = r.nbRangeeUtilise;
        nb_util_tot = r.nb_util_tot;
        tauxRemplissage = r.tauxRemplissage;
    }
    
    public Remplissage(Salle salle, int demarrage){
        this.data = new ArrayList<RemplissageGroupeRangee>();
        this.salle = new Salle(salle);
        sommeDist = 0;
        nbRangeeUtilise = 0;
        nb_util_tot = 0;
        
        this.algoNaive(demarrage);
        
        tauxRemplissage = (float)nb_util_tot/(float)salle.nb_place_tot;
    }

    //ici le parametre demarrage est la rangee a la quelle on commance a remplire;
    public void algoNaive(int demarrage){
        int k = demarrage;
        // tant quil reste des reservation et des rangee de libre
        while(!salle.reservations.isEmpty() &&  k < salle.rangees.size()){
            RempliRangee(k);
            nbRangeeUtilise++;
            k += salle.P + 1;
        }
    }

    public boolean RestePlace(int reservation, int decal, int capacitee){
        return (reservation + decal*salle.Q <= capacitee);
    }

    public void AlgoEnum(int k){
        // on minimise le nombre de range ou les distance de la scene
        // int min = 5000;
        // ou on maximise le taux de remplissage
        float max = 0.0f ;
        Remplissage rtemp = null;
        //on essaye de commencer par des rangee differente et regarder le quelle est optimal;
        for (int i = k; i <= salle.P+k; i++) {
            if(i >= salle.rangees.size())
                break;
            Remplissage r = new Remplissage(this);
            r.RempliRangee(i);
            //on passe a la prochaine remplissable
            r.AlgoEnum(i+ salle.P +1);
            if (r.tauxRemplissage > max) {
                rtemp = r;
                max = r.tauxRemplissage;
            }
        }

        // on prend la meilleur solution 
        if(rtemp == null) return;
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

    private void RempliRangee(int i){
        int decal = 0;
        int nbutil = 0;
        List<Integer> gs = new ArrayList<Integer>();
        for (Rangees r : salle.rangees.get(i)) {
            if(salle.reservations.isEmpty())
                break;
            while(RestePlace(salle.reservations.get(0).nombreSpectateur, decal, r.capacitee)){
                r.capacitee -= salle.reservations.get(0).nombreSpectateur + decal*salle.Q;
                nbutil += salle.reservations.get(0).nombreSpectateur;
                gs.add(salle.reservations.get(0).numGroupeSpectateur);
                salle.reservations.remove(0);
                decal = 1;
                if(salle.reservations.isEmpty())
                    break;
            }
            decal = 0;
            sommeDist += i;
            data.add(new RemplissageGroupeRangee(r.groupe, i, nbutil, gs));
            gs.clear();
            nb_util_tot += nbutil;
            nbutil = 0;
            decal = 0;
        }

        tauxRemplissage = (float)nb_util_tot / (float)salle.nb_place_tot;
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
