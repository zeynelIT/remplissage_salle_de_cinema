import java.util.ArrayList;
import java.util.List;

public class GroupeDeRangees {
        List<Rangees> rangees;

        public GroupeDeRangees(){
            rangees = new ArrayList<Rangees>();
        }

        public GroupeDeRangees(GroupeDeRangees gr){
            this.rangees = new ArrayList<Rangees>();
            for (Rangees rangees2 : gr.rangees) {
                this.rangees.add(new Rangees(rangees2));
            }
        }

        

        public void addRangees(Rangees rangee){
            this.rangees.add(new Rangees(rangee.capacitee,rangee.distanceDeLaScene));
        }
}
