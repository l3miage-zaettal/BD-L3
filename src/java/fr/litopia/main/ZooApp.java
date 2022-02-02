package fr.litopia.main;

import fr.litopia.dao.AnimalDAO;
import fr.litopia.dao.CageDAO;
import fr.litopia.modele.Animal;
import fr.litopia.modele.Cage;
import fr.litopia.modele.Sexe;
import fr.litopia.utils.TheConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static fr.litopia.modele.Sexe.askSexe;

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
                switch (choix) {
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
            } while (choix != 0);
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
     *
     * @throws SQLException
     */
    private static void affichageAnimaux() throws SQLException {
        Connection conn = TheConnection.getInstance();
        final AnimalDAO AD = new AnimalDAO(conn);
        conn.beginRequest();
        Set<Animal> setAnimals = AD.readAll();
        for (Animal a : setAnimals) {
            System.out.println(a.toString());
        }
        conn.rollback();
    }


    /**
     * Ajouter un animal en choisissant sa cage de destintation
     */
    private static void ajouterAnimal() throws SQLException {
        Connection conn = TheConnection.getInstance();
        conn.beginRequest();
        try {
            Animal a = genererAnimal();
			AnimalDAO AD = new AnimalDAO(conn);
			Boolean check = AD.create(a);
			if (check) {
				System.out.println("Animal ajouté avec succès");
				conn.commit();
			}else {
				System.out.println("Erreur lors de l'ajout de l'animal");
				conn.rollback();
			}
        } catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Annulation de votre création d'animal car une erreur est survenue.");
            conn.rollback();
        }
    }

    /**
     * Methode qui permet de generer un animal à partir de la console
     */
    private static Animal genererAnimal() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Animal a = new Animal();
        System.out.println("Entrez le nom de l'animal");
        String nom = sc.nextLine();
        System.out.println("Entrez le sexe de l'animal");
        Sexe sexe = askSexe();
        System.out.println("Entrez le type de l'animal");
        String type = sc.nextLine();
        System.out.println("Entrez le pays d'origine de l'animal");
        String pays = sc.nextLine();
        System.out.println("Entrez l'année de naissance de l'animal");
		int anneeNaissance = lireEntier(2000);

        CageDAO cDAO = new CageDAO(TheConnection.getInstance());
        System.out.println("Entrez le NoCage de la cage parmi les suivants :");
        Set<Cage> lesCages = cDAO.readAll();
        for (Cage c : lesCages) {
            System.out.println(c.toString());
        }
        //recuperer le NoCage tant que le numero ne correspond pas a une cage existante
        Integer NoCage = lireEntier(0);

        //Conversion du set de cage en un tableau de numero de cage
        List<Integer> lesNoCages = lesCages.stream().map(Cage::getNoCage).toList();
        while (!lesNoCages.contains(NoCage)) {
            System.out.println("Ce numero de cage n'existe pas, veuillez en entrer un autre");
            NoCage = lireEntier(0);
        }
        final Integer finalNoCage = NoCage;
		//Filtration du set de cage pour récupérer la cage correspondante
        Cage cage = lesCages.stream().filter(c -> c.getNoCage().equals(finalNoCage)).findFirst().get();
        a.setLaCage(cage);
        a.setFonctionCage(cage.getFonction());
        a.setNomA(nom);
        a.setSexe(sexe);
        a.setType(type);
        a.setPays(pays);
        a.setAnNais(anneeNaissance);
        return a;
    }

	/**
	 * Methode qui lis un integer dans la console tant que celui-ci n'est pas un entier
	 * @return Integer
	 */
	private static Integer lireEntier(int min) {
		Scanner sc = new Scanner(System.in);
		Integer entier = -1;
		while (entier<min) {
			try {
				if (entier<min) System.out.println("Veuillez entrer un entier supérieur à "+min);
				entier = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Veuillez entrer un entier positif");
				sc.next();
			}
		}
		return entier;
	}

    /**
     * Ajouter une cage à la liste de cages gardés par un gardien
     */
    private static void ajouterCageGardien() throws SQLException {
        Connection conn = TheConnection.getInstance();

        conn.commit();
    }

}