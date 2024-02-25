public class test {
    public static void main(String[] args) {
        Salle s = new Salle();
        s.readSalleData("DATA_A/Salle02/Salle02.txt");
        s.readConstraintData("DATA_A/Salle02/Contraintes01..txt");
        s.readReservationData("DATA_A/Salle02/Reservations01.txt");
        // System.out.println(s.ToString());
        Remplissage remplissage = new Remplissage(s);
        System.out.println(remplissage.ToString());
        Checker2 check = new Checker2();
        check.readSalleData("DATA_A/Salle02/Salle02.txt");
        check.readConstraintData("DATA_A/Salle02/Contraintes01..txt");
        check.readReservationData("DATA_A/Salle02/Reservations01.txt");
        System.out.println(check.ToString());
        System.out.println(check.Check("resSalle01.res"));
        
    }
}
