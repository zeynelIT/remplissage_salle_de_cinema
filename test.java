public class test {
    public static void main(String[] args) {
        Salle s = new Salle();
        s.readSalleData("DATA_A/Salle01/Salle01.txt");
        s.readConstraintData("DATA_A/Salle01/Contraintes01..txt");
        s.readReservationData("DATA_A/Salle01/Reservations01.txt");
        System.out.println(s.ToString());
        Remplissage remplissage = new Remplissage(s);
        System.out.println(remplissage.data.size());
        System.out.println(remplissage.salle.ToString());
        System.out.println(remplissage.ToString());
    }
}
