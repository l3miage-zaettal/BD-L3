package fr.litopia.modele;

import java.util.Objects;
import java.util.Scanner;

public enum Sexe {
    femelle,
    male,
    hermaphrodite;

    public static Sexe fromString(String s){
        if (Objects.equals(s, "femelle")) return Sexe.femelle;
        if (Objects.equals(s, "male")) return Sexe.male;
        if (Objects.equals(s, "hermaphrodite")) return Sexe.hermaphrodite;
        throw new IllegalArgumentException(s+" is not a sexe");
    }

    public static Sexe fromInt(int v){
        if (v==1) return Sexe.femelle;
        if (v==2) return Sexe.male;
        if (v==3) return Sexe.hermaphrodite;
        throw new IllegalArgumentException(v+" is not a value for sexe");
    }

    public static Sexe askSexe(){
        System.out.println("Quel sexe choisisez vous ?");
        System.out.println("[1] Femelle");
        System.out.println("[2] Male");
        System.out.println("[3] hermaphrodite");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        boolean success = false;
        while (true){
            try{
                return Sexe.fromInt(i);
            }catch (IllegalArgumentException e){
                System.out.println("Veuillez choisir un sexe valide");
                i = s.nextInt();
            }
        }
    }
}
