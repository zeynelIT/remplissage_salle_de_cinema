public class test {
    public static void main(String[] args) {
        Salle s = new getSalleData().readSalleData("DATA_A/Salle01/Salle01.txt");
        System.out.println(s.ToString());
    }
}
