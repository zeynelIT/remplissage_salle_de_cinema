import java.util.ArrayList;
import java.util.List;

public class GroupeDeRangees {
        List<Rangees> rangees;

        public GroupeDeRangees(){
            rangees = new ArrayList<Rangees>();
        }

        public GroupeDeRangees(List<Rangees> rangees){
            this.rangees = new ArrayList<Rangees>(rangees);
        }

        public void addRangees(Rangees rangee){
            this.rangees.add(new Rangees(rangee.capacitee,rangee.distanceDeLaScene));
        }
}
