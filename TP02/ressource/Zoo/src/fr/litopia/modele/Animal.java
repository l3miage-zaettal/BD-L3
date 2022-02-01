package fr.litopia.modele;

import java.util.Set;

public class Animal {
  private Integer an_id;
  private String nomA;
  private String sexe;
  private String type;
  private String fonction_cage;
  private String pays;
  private int anNais;
  private Cage laCage;
  private Set<String> lstMaladies;

  public Animal() {
  }

  public Animal(Integer id) {
    an_id = id;;
	  }
  
  public long getAn_id() {
    return an_id;
  }

  public void setAn_id(Integer id) {
    an_id = id;
  }

  public String getNomA() {
    return nomA;
  }  

  public void setNomA(String n) {
    nomA = n;
  }

  
  public String getSexe() {
    return sexe;
  }

  public void setSexe(String s) {
    sexe = s;
  }
 
  public String getType() {
    return type;
  }  

  public void setType(String t) {
    type = t;
  }
 
  public String getFonctionCage() {
    return fonction_cage;
  }  

  public void setFonctionCage(String s) {
    fonction_cage = s;
  }

  public String getPays() {
    return pays;
  }  

  public void setPays(String p) {
    pays = p;
  }

  public int  getAnNais() {
    return anNais;
  }
 
  public void setAnNais(int a) {
    anNais = a;
  }

    public Cage getLaCage() {
    return laCage;
  }  

  public void setLaCage(Cage C) {
    laCage = C;
  }

  public Set<String> getMaladies() {
	    return lstMaladies;
	  }
	  
  public void setMaladies(Set<String> l) {
	    lstMaladies = l;
	  }

  @Override
  public String toString(){
    StringBuilder stringBuilder = new StringBuilder("=====================");
    stringBuilder.append("\nNom:");
    stringBuilder.append("\n"+getLaCage());
    for (String mal : getMaladies()){
      stringBuilder.append("\n").append(mal);
    }
    return stringBuilder.toString();
  }
}
