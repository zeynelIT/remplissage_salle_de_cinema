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
}
