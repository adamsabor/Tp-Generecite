public abstract class Media {
    private String titre;
    private int anneePublication;

    // Constructeur absract vu en cours
    public Media() {
        this.titre = "";
        this.anneePublication = 0;
    }

    // Constructeur paramétre
    public Media(String titre, int anneePublication) {
        this.titre = titre;
        this.anneePublication = anneePublication;
    }

    // Getters
    public String getTitre() {
        return titre;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    // Méthode abstraite generiicité
    public abstract String getDescription();

    // toString
    @Override
    public String toString() {
        return titre + " (" + anneePublication + ")";
    }
}