package fr.litopia.modele;

import java.util.Set;

public class Cage {
    private Integer noCage;
    private String fonction;
    private Integer noAlle;
    private Set<Animal> lstAnimals;
    private Set<Gardien> lstGardiens;

    public Cage() {}

    public Cage(Integer c) {
        setNoCage(c);
    }

    public Integer getNoCage() {
        return noCage;
    }

    public void setNoCage(Integer c) throws IllegalArgumentException {
        // Restriction de la cage comme impose par la BD dan la constraint LesCages_C2
        if (c < 1 || c > 999) {
            throw new IllegalArgumentException("noAlle must be between 1 and 999");
        }
        noCage = c;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String f) {
        fonction = f;
    }

    public Integer getNoAlle() {
        return noAlle;
    }

    public void setNoAlle(Integer n) throws IllegalArgumentException {
        // Restriction de la cage comme impose par la BD dans la constraint LesCages_C3
        if (n < 1 || n > 999) {
            throw new IllegalArgumentException("noAlle must be between 1 and 999");
        }
        noAlle = n;
    }

    public Set<Animal> getAnimals() {
        return lstAnimals;
    }

    public void setAnimals(Set<Animal> l) {
        lstAnimals = l;
    }

    public Set<Gardien> getGardiens() {
        return lstGardiens;
    }

    public void setGardiens(Set<Gardien> l) {
        lstGardiens = l;
    }

    @Override
    public String toString() {
        return "Cage [noCage=" + noCage + ", fonction=" + fonction + ", noAlle=" + noAlle + "]";
    }
}
