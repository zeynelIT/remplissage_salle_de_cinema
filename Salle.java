import java.util.ArrayList;
import java.util.List;

public class Salle {
    List<GroupeDeRangees> groupes;

    public Salle(){
        groupes = new ArrayList<GroupeDeRangees>();
    }

    public Salle(List<GroupeDeRangees> groupes){
        this.groupes = new ArrayList<GroupeDeRangees>(groupes);
    }

    public void addGroupe(GroupeDeRangees groupe){
        groupes.add(new GroupeDeRangees(groupe.rangees));
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
        return res;
    }
}
