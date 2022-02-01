package fr.litopia.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import fr.litopia.dao.AnimalDAO;
import fr.litopia.modele.Animal;
import fr.litopia.utils.TheConnection;

public class ZooApp {

	public static void main(String args[]) {

		try {
			
			/* Menu utilisateur */
			System.out.println("Bienvenue sur l'application de gestion du Zoo ! :D");
			Scanner sc = new Scanner(System.in);
			int choix;
			do {
				System.out.println("Que souhaitez-vous faire ?");
				System.out.println("[1] Affichage Animaux");
				System.out.println("[2] Ajouter un animal dans une cage");
				System.out.println("[3] Ajouter une cage à un gardien");
				System.out.println("[0] Quitter l'application.");
				choix = sc.nextInt();
				switch(choix){
				case 1:
					System.out.flush();
					affichageAnimaux();
					break;
				case 2:
					System.out.flush();
					ajouterAnimal();
					break;
				case 3:
					System.out.flush();
					ajouterCageGardien();
					break;
				default:
					System.out.flush();
					break;
				}
			} while(choix != 0); 
			sc.close(); // fermeture du scanner
			TheConnection.close();
			System.out.println("bye.");

			// traitement d'exception
		} catch (SQLException e) {
			TheConnection.close();
			System.err.println("failed");
			System.out.println("Affichage de la pile d'erreur");
			e.printStackTrace(System.err);
			System.out.println("Affichage du message d'erreur");
			System.out.println(e.getMessage());
			System.out.println("Affichage du code d'erreur");
			System.out.println(e.getErrorCode());	    
		}
	}

	/**
	 * Afficher la liste des animaux avec leur numéro de cage.
	 * @throws SQLException 
	 */
	private static void affichageAnimaux() throws SQLException{
	  Connection conn=TheConnection.getInstance();
	  final AnimalDAO AD = new AnimalDAO(conn);
	  conn.beginRequest();
	  Set<Animal> setAnimals =  AD.readAll();
	  for(Animal a : setAnimals){
		  System.out.println(a.toString());
	  }
	  conn.rollback();
	}


	/**
	 * Ajouter un animal en choisissant sa cage de destintation
	 */
	private static void ajouterAnimal() throws SQLException{
	  Connection conn=TheConnection.getInstance();

	  conn.commit();
	}

	/**
	 * Ajouter une cage à la liste de cages gardés par un gardien
	 */
	private static void ajouterCageGardien() throws SQLException{
	  Connection conn=TheConnection.getInstance();

	  conn.commit();
	}

}