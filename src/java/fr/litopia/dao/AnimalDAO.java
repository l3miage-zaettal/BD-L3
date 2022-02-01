package fr.litopia.dao;

import fr.litopia.modele.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AnimalDAO extends DAO<Animal> {

	public AnimalDAO(Connection conn) throws SQLException{
		super(conn);
	}


	@Override
	public boolean create(Animal obj)throws SQLException {

		/* TO DO */

		return true;
	}

	@Override
	public Animal read(Object obj) throws SQLException{

		return null;
	}

	@Override
	public Set<Animal> readAll() throws SQLException {
		Set<Animal> animaux = new HashSet<>();

		String sql = "SELECT NOMA,SEXE,TYPE_AN,FONCTION_CAGE,PAYS,ANNAIS,NOCAGE FROM LESANIMAUX";
		PreparedStatement prepare = this.conn.prepareStatement(sql);
		ResultSet rs = prepare.executeQuery();

		while (rs.next()){
			//Récupération des données
			String nomA = rs.getString("NOMA");
			String sexe = rs.getString("SEXE");
			String typeAn = rs.getString("TYPE_AN");
			String fonctionCage = rs.getString("FONCTION_CAGE");
			String pays = rs.getString("PAYS");
			Integer annais = rs.getInt("ANNAIS");

			//Initialisation de l'animal
			final Animal animal = new Animal();
			animal.setNomA(nomA);
			animal.setSexe(sexe);
			animal.setType(typeAn);
			animal.setType(fonctionCage);
			animal.setPays(pays);
			animal.setAnNais(annais);
			animaux.add(animal);

			//Récupération de la cage
			//@TODO : faire le read de CageDAO
			CageDAO cageDAO = new CageDAO(this.conn);
			animal.setLaCage(cageDAO.read(rs.getString("NOCAGE")));


			//Récupération des maladies
			String sqlMal = "SELECT NOMM FROM LESMALADIES WHERE NOMA = ?";
			PreparedStatement prepareMal = this.conn.prepareStatement(sqlMal);
			prepareMal.setString(1, nomA);
			ResultSet rsMal = prepareMal.executeQuery();
			Set<String> maladies = new HashSet<>();
			while (rsMal.next()){
				maladies.add(rsMal.getString(1));
			}
			animal.setMaladies(maladies);
		}

		return animaux;
	}

	@Override
	public boolean update(Animal animal) throws SQLException{
		return false;
	}

	@Override
	public boolean delete(Animal obj) throws SQLException{
		return false;
	}

}
