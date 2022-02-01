package fr.litopia.modele;

import java.util.HashSet;
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
    lstMaladies = new HashSet<String>();
  }

  public Animal(Integer id) {
    this();
    an_id = id;
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
    StringBuilder stringBuilder = new StringBuilder("=====================").append('\n');
    stringBuilder.append("Nom:").append(nomA).append('\n');
    if (sexe != null)
      stringBuilder.append("Sexe:").append(sexe).append('\n');
    if (type != null)
      stringBuilder.append("Type:").append(type).append('\n');
    if (fonction_cage != null)
      stringBuilder.append("Fonction de Cage:").append(fonction_cage).append('\n');
    if (pays != null)
      stringBuilder.append("Pays:").append(pays).append('\n');
    if (anNais != 0)
      stringBuilder.append("Année de naissance:").append(anNais).append('\n');
    if(getLaCage() != null)
      stringBuilder.append(getLaCage()).append('\n');
    if(getMaladies().size() != 0 ) {
      stringBuilder.append("Maladies:").append('\n');
      for (String mal : getMaladies()) {
        stringBuilder.append('\t').append(mal).append('\n');
      }
    }
    return stringBuilder.toString();
  }
}
