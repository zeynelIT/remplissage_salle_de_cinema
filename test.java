public class test {
    public static void main(String[] args) {
        Salle s = new Salle();
        s.readSalleData("DATA_A/Salle06/Salle06.txt");
        s.readConstraintData("DATA_A/Salle06/Contraintes01..txt");
        s.readReservationData("DATA_A/Salle06/Reservations05.txt");
        System.out.println(s.ToString());
        Remplissage remplissage = new Remplissage(s);
        System.out.println(remplissage.data.size());
        System.out.println(remplissage.salle.ToString());
        System.out.println(remplissage.ToString());
    }
}
