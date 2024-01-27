public class Rangees {
    int capacitee;
    int distanceDeLaScene;

    public Rangees(){
        capacitee = 0;
        distanceDeLaScene = 0;
    }

    public Rangees(int capacitee, int distanceDeLaScene){
        this.capacitee = capacitee;
        this.distanceDeLaScene = distanceDeLaScene;
    }

    public Rangees(Rangees r){
        capacitee = r.capacitee;
        distanceDeLaScene = r.distanceDeLaScene;
    }
}
