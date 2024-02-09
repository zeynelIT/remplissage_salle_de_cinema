import java.io.*;
import java.util.*;

public class Checker {
    private int[][] salle; // salle[i][j] = capacité de la rangée j du groupe i; -1 si non utilisée
    private int[][] distances; // distances[i][j] = distance de la rangée j du groupe i
    private int P, K, Q; // Contraintes
    private List<Integer> reservations; // Tailles des réservations
    private List<String> planRemplissage; // Plan de remplissage lu depuis le fichier de résultats
    private List<PlacementDetail> placements = new ArrayList<>();

    public Checker() {
        reservations = new ArrayList<>();
        planRemplissage = new ArrayList<>();
    }

    public void readData(String salleFile, String contraintesFile, String reservationsFile, String resultFile) {
        try {
            // Lecture de la configuration de la salle
            Scanner scanner = new Scanner(new File(salleFile));
            int G = scanner.nextInt(); // Nombre de groupes
            salle = new int[G][];
            distances = new int[G][];
            for (int i = 0; i < G; i++) {
                int R = scanner.nextInt(); // Nombre de rangées dans le groupe i
                salle[i] = new int[R];
                distances[i] = new int[R];
                for (int j = 0; j < R; j++) {
                    salle[i][j] = scanner.nextInt();
                    distances[i][j] = scanner.nextInt();
                }
            }
            scanner.close();

            // Lecture des contraintes
            scanner = new Scanner(new File(contraintesFile));
            P = scanner.nextInt();
            K = scanner.nextInt();
            Q = scanner.nextInt();
            scanner.close();

            // Lecture des réservations
            scanner = new Scanner(new File(reservationsFile));
            int N = scanner.nextInt(); // Nombre de réservations
            for (int i = 0; i < N; i++) {
                reservations.add(scanner.nextInt());
            }
            scanner.close();

            // Lecture du plan de remplissage
            scanner = new Scanner(new File(resultFile));
            scanner.nextLine(); // Ignorer la première ligne (informations d'en-tête)

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Non places")) {
                    break; // Arrêter la lecture lorsque l'on atteint la liste des non placés
                }
                // Créer un nouvel objet PlacementDetail avec la ligne et l'ajouter à la liste
                placements.add(new PlacementDetail(line));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erreur lors de la lecture des fichiers: " + e.getMessage());
        }
    }


    public boolean verifyConstraints() {
        boolean[][] occupation = new boolean[salle.length][];
        Map<Integer, Set<Integer>> reservationGroups = new HashMap<>();
        
        for (int i = 0; i < salle.length; i++) {
            occupation[i] = new boolean[salle[i].length];
        }

        for (String line : planRemplissage) {
            String[] parts = line.split(" ");
            if (parts.length < 4) continue;
            
            int groupe = Integer.parseInt(parts[0]) - 1;
            int rangée = Integer.parseInt(parts[1]) - 1;
            int reservationId = Integer.parseInt(parts[2]);
            int taille = Integer.parseInt(parts[3]);

            // Vérifier la capacité
            if (taille > salle[groupe][rangée]) {
                System.out.println("Contrainte de capacité violée pour le groupe " + groupe + " rangée " + rangée);
                return false;
            }

            occupation[groupe][rangée] = true;

            // Suivre les groupes utilisés par chaque réservation
            reservationGroups.putIfAbsent(reservationId, new HashSet<>());
            reservationGroups.get(reservationId).add(groupe);
        }

        // Vérifier la contrainte de distanciation
        if (P > 0) {
            for (int i = 0; i < occupation.length; i++) {
                for (int j = 0; j < occupation[i].length; j++) {
                    if (occupation[i][j]) {
                        if ((j - P >= 0 && occupation[i][j - P]) || (j + P < occupation[i].length && occupation[i][j + P])) {
                            System.out.println("Contrainte de distanciation violée au groupe " + i + " rangée " + j);
                            return false;
                        }
                    }
                }
            }
        }

        // Vérifier le nombre maximum de groupes par réservation
        for (Map.Entry<Integer, Set<Integer>> entry : reservationGroups.entrySet()) {
            if (entry.getValue().size() > Q) {
                System.out.println("Contrainte sur le nombre de groupes violée pour la réservation " + entry.getKey() + ". Utilise " + entry.getValue().size() + " groupes.");
                return false;
            }
        }
        
        return true;
    }

    public void displayPlan() {
        System.out.println("Plan de remplissage de la salle :");
        for (PlacementDetail placement : placements) {
            System.out.println("Groupe " + placement.groupe + " Rangée " + placement.rangée + ": " +
                            "Places utilisées " + placement.nbPlacesUtilisées + " par les groupes de spectateurs " +
                            placement.numsGroupesSpectateurs);
        }
    }

    public static void main(String[] args) {
        Checker checker = new Checker();
        checker.readData("DATA_A/Salle06/Salle06.txt", "DATA_A/Salle06/Contraintes01..txt", "DATA_A/Salle06/Reservations01.txt", "Remplissage.res");
        if (checker.verifyConstraints()) {
            System.out.println("Toutes les contraintes sont respectées.");
            checker.displayPlan();
        } else {
            System.out.println("Des contraintes ne sont pas respectées.");
        }
    }
}
