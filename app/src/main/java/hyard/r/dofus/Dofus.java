package hyard.r.dofus;

/**
 * Created by admin on 29/05/2018.
 */

public class Dofus {

    /**
     * Propriétés
     */
    private int idDofus;
    private String nomDofus;
    private int levelDofus;
    private String descriptionDofus;
    private String effetDofus;
    private String descripEffet;

    /**
     * Constructeur
     * @param idDofus
     * @param nomDofus
     * @param levelDofus
     * @param descriptionDofus
     * @param effetDofus
     * @param descripEffet
     */
    public Dofus(int idDofus, String nomDofus, int levelDofus, String descriptionDofus, String effetDofus, String descripEffet){
        this.idDofus = idDofus;
        this.nomDofus = nomDofus;
        this.levelDofus = levelDofus;
        this.descriptionDofus = descriptionDofus;
        this.effetDofus = effetDofus;
        this.descripEffet = descripEffet;
    }

    /**
     * Getters
     */
    public int getIdDofus() {
        return idDofus;
    }

    public String getNomDofus() {
        return nomDofus;
    }

    public int getLevelDofus() {
        return levelDofus;
    }

    public String getDescriptionDofus() {
        return descriptionDofus;
    }

    public String getEffetDofus() {
        return effetDofus;
    }

    public String getDescripEffet() {
        return descripEffet;
    }

    /**
     * Setters
     */
    public void setIdDofus(int idDofus) {
        this.idDofus = idDofus;
    }

    public void setNomDofus(String nomDofus) {
        this.nomDofus = nomDofus;
    }

    public void setLevelDofus(int levelDofus) {
        this.levelDofus = levelDofus;
    }

    public void setDescriptionDofus(String descriptionDofus) {
        this.descriptionDofus = descriptionDofus;
    }

    public void setEffetDofus(String effetDofus) {
        effetDofus = effetDofus;
    }

    public void setDescripEffet(String descripEffet) {
        this.descripEffet = descripEffet;
    }
}
