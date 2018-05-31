package hyard.r.dofus;

/**
 * Created by admin on 28/05/2018.
 */

public class ClasseD {

    /**
     * Propriétés
     */
    private int idClasse;
    private String nom;
    private int difficulte;
    private String description;
    private String dieu;

    /**
     * Constructeur
     * @param nom
     * @param difficulte
     * @param description
     * @param dieu
     */
    public ClasseD(int idClasse, String nom, int difficulte, String description, String dieu){
        this.idClasse = idClasse;
        this.nom = nom;
        this.difficulte = difficulte;
        this.description = description;
        this.dieu = dieu;
    }

    /**
     * Getters
     * @return
     */

    public int getIdClasse() {
        return idClasse;
    }

    public String getNom() {
        return nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public String getDescription() {
        return description;
    }

    public String getDieu() {
        return dieu;
    }

    /**
     * Setters
     * @param idClasse
     */

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDieu(String dieu) {
        this.dieu = dieu;
    }
}
