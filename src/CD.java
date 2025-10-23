public class CD extends Media implements Empruntable {
    private String artiste;
    private int duree;
    private boolean disponible;

    // Constructeur
    public CD(String titre, int anneePublication, String artiste, int duree) {
        super(titre, anneePublication);
        this.artiste = artiste;
        this.duree = duree;
        this.disponible = true;
    }

    // Redéfinition de getDescription
    @Override
    public String getDescription() {
        return "CD de " + artiste + ", durée : " + duree + " min.";
    }

    // ÉTAPE 7 : Implémentation de l'interface Empruntable
    @Override
    public void emprunter() {
        if (disponible) {
            disponible = false;
            System.out.println("✓ L'album '" + getTitre() + "' a été emprunté.");
        } else {
            System.out.println("✗ L'album '" + getTitre() + "' n'est pas disponible.");
        }
    }

    @Override
    public void retourner() {
        disponible = true;
        System.out.println("✓ L'album '" + getTitre() + "' a été retourné.");
    }

    @Override
    public boolean estDisponible() {
        return disponible;
    }
}