package fr.litopia.modele;

import java.util.Set;

public class Animal {
  private String nomA;
  private Sexe sexe;
  private String type;
  private String fonction_cage;
  private String pays;
  private int anNais;
  private Cage laCage;
  private Set<String> lstMaladies;

  public Animal() {
  }

  public Animal(String n) {
    nomA = n;
  }


  public String getNomA() {
    return nomA;
  }  

  public void setNomA(String n) {
    nomA = n;
  }

  
  public Sexe getSexe() {
    return sexe;
  }

  public void setSexe(Sexe s) {
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
    if(a<2000) throw new IllegalArgumentException("Année de naissance invalide");
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
