public class test {
    public static void main(String[] args) {
        // Salle s = new getSalleData().readSalleData("DATA_A/Salle01/Salle01.txt");
        Salle s = new Salle();
        s.readSalleData("DATA_A/Salle01/Salle01.txt");
        s.readConstraintData("DATA_A/Salle01/Contraintes01..txt");
        System.out.println(s.ToString());
    }
}
