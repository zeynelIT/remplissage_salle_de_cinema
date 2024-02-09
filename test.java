public class test {
    public static void main(String[] args) {
        Salle s = new Salle();
        s.readSalleData("DATA_A/Salle06/Salle06.txt");
        s.readConstraintData("DATA_A/Salle06/Contraintes01..txt");
        s.readReservationData("DATA_A/Salle06/Reservations01.txt");
        Remplissage remplissage = new Remplissage(s);
        System.out.println(remplissage.ToString());
    }
}
