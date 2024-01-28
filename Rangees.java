public class Rangees {
    int capacitee;
    int distanceDeLaScene;
    int groupe;

    public Rangees(){
        capacitee = 0;
        distanceDeLaScene = 0;
        groupe = 0;
    }

    public Rangees(int capacitee, int distanceDeLaScene, int groupe){
        this.capacitee = capacitee;
        this.distanceDeLaScene = distanceDeLaScene;
        this.groupe = groupe;
    }

    public Rangees(Rangees r){
        capacitee = r.capacitee;
        distanceDeLaScene = r.distanceDeLaScene;
        groupe = r.groupe;
    }
}
