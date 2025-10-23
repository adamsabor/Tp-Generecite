public class Livre extends Media implements Empruntable {
    private String auteur;
    private int nbPages;
    private boolean disponible;

    // Constructeur
    public Livre(String titre, int anneePublication, String auteur, int nbPages) {
        super(titre, anneePublication);
        this.auteur = auteur;
        this.nbPages = nbPages;
        this.disponible = true;
    }

    // Redéfinition de getDescription
    @Override
    public String getDescription() {
        return "Livre de " + auteur + ", " + nbPages + " pages.";
    }

    // ÉTAPE 7 : Implémentation de l'interface Empruntable
    @Override
    public void emprunter() {
        if (disponible) {
            disponible = false;
            System.out.println("✓ Le livre '" + getTitre() + "' a été emprunté.");
        } else {
            System.out.println("✗ Le livre '" + getTitre() + "' n'est pas disponible.");
        }
    }

    @Override
    public void retourner() {
        disponible = true;
        System.out.println("✓ Le livre '" + getTitre() + "' a été retourné.");
    }

    @Override
    public boolean estDisponible() {
        return disponible;
    }
}