package fr.litopia.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import fr.litopia.modele.Animal;

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

		PreparedStatement ps = conn.prepareStatement("Select * from LESANIMAUX");
		ResultSet resultSet =  ps.executeQuery();

		while (resultSet.next()){
			String nomA = resultSet.getString("NOMA");
			String sexe = resultSet.getString("SEXE");
			String typeAn = resultSet.getString("TYPE_AN");
			String fonctionCage = resultSet.getString("FONCTION_CAGE");
			String pays = resultSet.getString("PAYS");
			String annais = resultSet.getString("ANNAIS");
			String nocage = resultSet.getString("NOCAGE");
			String nb_maladies = resultSet.getString("NB_MALADIES");
			final Animal animal = new Animal();
			animal.setNomA(nomA);
			animal.setSexe(sexe);
			animal.setType(typeAn);
			animal.setType(fonctionCage);

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
