package hyard.r.dofus;

/**
 * Created by admin on 29/05/2018.
 */

public class Metier {

    /**
     * Propriétés
     */
    private int idMetier;
    private String nomMetier;
    private String descriptionMetier;
    private int level;

    /**
     * Constructeur
     * @param idMetier
     * @param nomMetier
     * @param descriptionMetier
     * @param level
     */
    public Metier(int idMetier, String nomMetier, String descriptionMetier, int level){
        this.idMetier = idMetier;
        this.nomMetier = nomMetier;
        this.descriptionMetier = descriptionMetier;
        this.level = level;
    }

    /**
     * Getters
     */
    public int getIdMetier() {
        return idMetier;
    }

    public String getNomMetier() {
        return nomMetier;
    }

    public String getDescriptionMetier() {
        return descriptionMetier;
    }

    public int getLevel() {
        return level;
    }

    /**
     * Setters
     */
    public void setIdMetier(int idMetier) {
        this.idMetier = idMetier;
    }

    public void setNomMetier(String nomMetier) {
        this.nomMetier = nomMetier;
    }

    public void setDescriptionMetier(String descriptionMetier) {
        this.descriptionMetier = descriptionMetier;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
