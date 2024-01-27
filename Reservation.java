public class Reservation {
    int nombreSpectateur;
    int numGroupeSpectateur;

    public Reservation(int nombreSpectateur, int numGroupeSpectateur){
        this.nombreSpectateur = nombreSpectateur;
        this.numGroupeSpectateur = numGroupeSpectateur;
    }

    public Reservation(Reservation r){
        nombreSpectateur = r.nombreSpectateur;
        numGroupeSpectateur = r.numGroupeSpectateur;
    }
}
