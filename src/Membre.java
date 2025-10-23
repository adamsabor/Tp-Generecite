import java.util.ArrayList;
import java.util.List;

public class Membre {
    private String nom;
    private int id;
    private List<Media> mediasEmpruntes;

    // Constructeur
    public Membre(String nom, int id) {
        this.nom = nom;
        this.id = id;
        this.mediasEmpruntes = new ArrayList<>();
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    // Emprunter un média
    public void emprunterMedia(Media media) {
        mediasEmpruntes.add(media);
    }

    // toString
    @Override
    public String toString() {
        return "Membre {" + nom + ", " + id + "}";
    }
}