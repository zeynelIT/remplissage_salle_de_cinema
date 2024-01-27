import java.util.ArrayList;
import java.util.List;

public class RemplissageGroupeRangee {
    int numGroupe, numRangee, nbPlaceUtilisee;
    List<Integer> numGroupeSpectateur;

    public RemplissageGroupeRangee(int numGroupe, int numRangee, int nbPlaceUtilisee, List<Integer> numGroupeSpectateur){
        this.nbPlaceUtilisee = nbPlaceUtilisee;
        this.numGroupe = numGroupe;
        this.numGroupeSpectateur = new ArrayList<Integer>(numGroupeSpectateur);
        this.numRangee = numRangee;
    }

    public RemplissageGroupeRangee(RemplissageGroupeRangee rg){
        this.nbPlaceUtilisee = rg.nbPlaceUtilisee;
        this.numGroupe = rg.numGroupe;
        this.numGroupeSpectateur = new ArrayList<Integer>(rg.numGroupeSpectateur);
        this.numRangee = rg.numRangee;
    }
}
