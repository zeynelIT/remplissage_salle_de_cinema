import java.util.List;
import java.util.ArrayList;

public class PlacementDetail {
    int groupe;
    int rangée;
    List<Integer> numsGroupesSpectateurs = new ArrayList<>();
    int nbPlacesUtilisées;

    public PlacementDetail(String line) {
        String[] parts = line.split(" ");
        this.groupe = Integer.parseInt(parts[0]);
        this.rangée = Integer.parseInt(parts[1]);
        this.nbPlacesUtilisées = Integer.parseInt(parts[parts.length - 1]);
        for (int i = 2; i < parts.length - 1; i++) {
            this.numsGroupesSpectateurs.add(Integer.parseInt(parts[i]));
        }
    }
}