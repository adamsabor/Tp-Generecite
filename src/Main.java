import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // On crée nos collections pour la médiathèque
        List<Media> medias = new ArrayList<>();
        Set<Membre> membres = new HashSet<>();
        Map<Membre, List<Media>> emprunts = new HashMap<>();


        Livre manga1 = new Livre("One Piece", 1997, "Eiichiro Oda", 200);
        Livre manga2 = new Livre("Attack on Titan", 2013, "Hajime Isayama", 180);
        Livre manga3 = new Livre("Naruto", 1999, "Masashi Kishimoto", 192);
        CD album1 = new CD("daft punk", 2013, "get lucky", 42);

        medias.add(manga1);
        medias.add(manga2);
        medias.add(manga3);
        medias.add(album1);

        // Inscription des membres
        Membre adam = new Membre("Adam Sabor", 1);

        membres.add(adam);


        // Adam emprunte des mangas et un album
        adam.emprunterMedia(manga1);
        adam.emprunterMedia(album1);


        // On remplit la map pour suivre qui a emprunté quoi
        emprunts.put(adam, new ArrayList<>());


        emprunts.get(adam).add(manga1);
        emprunts.get(adam).add(album1);


        // Affichage de tout le catalogue
        System.out.println("=== Catalogue complet de la médiathèque ===");
        afficherListe(medias);

        // Recherche des trucs récents (après 2010)
        System.out.println("\n=== Sorties récentes (après 2010) ===");
        List<Media> recent = filtrer(medias, m -> m.getAnneePublication() > 2010);
        afficherListe(recent);

        // Membres dont le nom commence par A
        System.out.println("\n=== Membres dont le nom commence par A ===");
        List<Membre> membresA = filtrer(new ArrayList<>(membres), m -> m.getNom().startsWith("A"));
        afficherListe(membresA);

        // Tri par date de sortie (du plus récent au plus vieux)
        System.out.println("\n=== Médias triés par date (récent → ancien) ===");
        medias.sort(Comparator.comparing(Media::getAnneePublication).reversed()
                .thenComparing(Media::getTitre));
        afficherListe(medias);

        // Tri des mangas par auteur
        System.out.println("\n=== Mangas triés par auteur ===");
        List<Livre> mangas = Arrays.asList(manga1, manga2, manga3);
        mangas.sort(Comparator.comparing((Livre l) -> l.getDescription().split(" de ")[1].split(",")[0])
                .thenComparing(Livre::getTitre));
        afficherListe(mangas);

        // Test de la copie de collection
        System.out.println("\n=== Copie du catalogue ===");
        List<Media> backup = new ArrayList<>();
        copierCollection(medias, backup);
        System.out.println("Backup créé avec " + backup.size() + " éléments");
        afficherListe(backup);

        // Voir tous les médias actuellement empruntés (sans doublon)
        System.out.println("\n=== Médias actuellement empruntés ===");
        Set<Media> enCours = obtenirMediasEmpruntes(emprunts);
        System.out.println("Il y a " + enCours.size() + " médias différents en circulation");
        afficherListe(new ArrayList<>(enCours));

        // Filtrer pour avoir que les mangas
        System.out.println("\n=== Uniquement les mangas ===");
        List<Media> justMangas = filtrer(medias, m -> m instanceof Livre);
        System.out.println("On a " + justMangas.size() + " mangas en stock");
        afficherListe(justMangas);

        // Et maintenant que les albums
        System.out.println("\n=== Uniquement les albums ===");
        List<Media> justAlbums = filtrer(medias, m -> m instanceof CD);
        System.out.println("On a " + justAlbums.size() + " albums en stock");
        afficherListe(justAlbums);

        // ========== ÉTAPE 7 - BONUS ==========

        // Test du polymorphisme avec afficherDetails()
        System.out.println("\n=== Test polymorphisme : afficherDetails() ===");
        for (Media m : medias) {
            m.afficherDetails();
        }

        // Test de l'interface Empruntable
        System.out.println("\n=== Test interface Empruntable ===");

        manga1.emprunter();
        album1.emprunter();
        manga1.emprunter(); // Essayer de l'emprunter une 2e fois

        System.out.println("\nStatut des médias :");
        System.out.println("One Piece disponible ? " + manga1.estDisponible());
        System.out.println("Thriller disponible ? " + album1.estDisponible());
        System.out.println("Attack on Titan disponible ? " + manga2.estDisponible());

        System.out.println();
        manga1.retourner();
        System.out.println("One Piece disponible ? " + manga1.estDisponible());

        // Liste d'Empruntable
        System.out.println("\n=== Manipulation via l'interface ===");
        List<Empruntable> empruntables = new ArrayList<>();
        empruntables.add(manga2);


        System.out.println("Emprunter tout en une fois :");
        for (Empruntable e : empruntables) {
            e.emprunter();
        }
    }

    // Affiche n'importe quelle liste
    public static <T> void afficherListe(List<T> liste) {
        for (T element : liste) {
            System.out.println(element);
        }
    }

    // Filtre une liste selon un critère
    public static <T> List<T> filtrer(List<T> liste, Predicate<T> critere) {
        List<T> resultat = new ArrayList<>();
        for (T element : liste) {
            if (critere.test(element)) {
                resultat.add(element);
            }
        }
        return resultat;
    }

    // Copie les éléments d'une collection vers une autre
    public static <T> void copierCollection(Collection<T> source, Collection<T> destination) {
        for (T element : source) {
            destination.add(element);
        }
    }

    // Récupère tous les médias empruntés sans doublons
    public static Set<Media> obtenirMediasEmpruntes(Map<Membre, List<Media>> emprunts) {
        Set<Media> mediasEmpruntes = new HashSet<>();

        for (List<Media> listeEmprunts : emprunts.values()) {
            mediasEmpruntes.addAll(listeEmprunts);
        }

        return mediasEmpruntes;
    }
}
