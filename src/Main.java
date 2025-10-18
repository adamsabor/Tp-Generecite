import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Étape 3 : Collections
        List<Media> medias = new ArrayList<>();
        Set<Membre> membres = new HashSet<>();
        Map<Membre, List<Media>> emprunts = new HashMap<>();

        // Créer des médias
        Livre livre1 = new Livre("Le Petit Prince", 1943, "Saint-Exupéry", 120);
        Livre livre2 = new Livre("1984", 2015, "Orwell", 300);
        CD cd1 = new CD("Abbey Road", 1969, "The Beatles", 47);
        CD cd2 = new CD("Thriller", 2012, "Michael Jackson", 42);

        medias.add(livre1);
        medias.add(livre2);
        medias.add(cd1);
        medias.add(cd2);

        // Créer des membres
        Membre membre1 = new Membre("Alice", 1);
        Membre membre2 = new Membre("Bob", 2);
        membres.add(membre1);
        membres.add(membre2);

        // Gérer les emprunts
        membre1.emprunterMedia(livre1);
        membre1.emprunterMedia(cd1);
        membre2.emprunterMedia(livre2);

        // Étape 4 : Méthodes génériques
        System.out.println("=== Tous les médias ===");
        afficherListe(medias);

        System.out.println("\n=== Médias publiés après 2010 ===");
        List<Media> mediasRecents = filtrer(medias, m -> m.getAnneePublication() > 2010);
        afficherListe(mediasRecents);

        System.out.println("\n=== Membres dont le nom commence par A ===");
        List<Membre> membresA = filtrer(new ArrayList<>(membres), m -> m.getNom().startsWith("A"));
        afficherListe(membresA);

        // Étape 5 : Tri
        System.out.println("\n=== Tri par année décroissante puis titre ===");
        medias.sort(Comparator.comparing(Media::getAnneePublication).reversed()
                .thenComparing(Media::getTitre));
        afficherListe(medias);

        System.out.println("\n=== Tri des livres par auteur puis titre ===");
        List<Livre> livres = Arrays.asList(livre1, livre2);
        livres.sort(Comparator.comparing((Livre l) -> l.getDescription().split(" de ")[1].split(",")[0])
                .thenComparing(Livre::getTitre));
        afficherListe(livres);
    }

    // Méthode générique pour afficher une liste
    public static <T> void afficherListe(List<T> liste) {
        for (T element : liste) {
            System.out.println(element);
        }
    }

    // Méthode générique pour filtrer une liste
    public static <T> List<T> filtrer(List<T> liste, Predicate<T> critere) {
        List<T> resultat = new ArrayList<>();
        for (T element : liste) {
            if (critere.test(element)) {
                resultat.add(element);
            }
        }
        return resultat;
    }
}