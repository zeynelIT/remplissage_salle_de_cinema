public class test {
    public static void main(String[] args) {
        Salle s = new Salle();
        s.readSalleData("DATA_A/Salle02/Salle02.txt");
        s.readConstraintData("DATA_A/Salle02/Contraintes01..txt");
        s.readReservationData("DATA_A/Salle02/Reservations03.txt");
        System.out.println(s.ToString());
        Remplissage remplissage = new Remplissage(s);
        System.out.println(remplissage.ToString());
    }
}
