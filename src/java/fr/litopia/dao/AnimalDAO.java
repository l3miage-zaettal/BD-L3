package fr.litopia.dao;

import fr.litopia.modele.Animal;
import fr.litopia.modele.Sexe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static fr.litopia.modele.Sexe.fromString;

public class AnimalDAO extends DAO<Animal> {

	public AnimalDAO(Connection conn) throws SQLException{
		super(conn);
	}


	@Override
	public boolean create(Animal obj)throws SQLException {
		//Enregistrement de l'animal
		String sql = "INSERT INTO LESANIMAUX (NOMA,SEXE,TYPE_AN,FONCTION_CAGE,PAYS,ANNAIS,NOCAGE,NB_MALADIES) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement prepare = this.conn.prepareStatement(sql);
		prepare.setString(1, obj.getNomA());
		prepare.setString(2, obj.getSexe().toString());
		prepare.setString(3, obj.getType());
		prepare.setString(4, obj.getFonctionCage());
		prepare.setString(5, obj.getPays());
		prepare.setInt(6, obj.getAnNais());
		prepare.setInt(7, obj.getLaCage().getNoCage());
		prepare.setInt(8, obj.getMaladies().size());

		int results = prepare.executeUpdate();
		int resultMaladies = 0;

		//Enregistrement des maladies
		if(obj.getMaladies() != null) {
			for (String maladie : obj.getMaladies()) {
				String sqlMal = "INSERT INTO LESMALADIES (NOMA,NOMM) VALUES (?,?)";
				PreparedStatement prepareMal = this.conn.prepareStatement(sqlMal);
				prepareMal.setString(1, obj.getNomA());
				prepareMal.setString(2, maladie);
				resultMaladies+=prepareMal.executeUpdate();
			}
		}

		return results == 1 && resultMaladies == obj.getMaladies().size();
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
			Sexe sexe = fromString(rs.getString("SEXE"));
			String typeAn = rs.getString("TYPE_AN");
			String fonctionCage = rs.getString("FONCTION_CAGE");
			String pays = rs.getString("PAYS");
			int annais = rs.getInt("ANNAIS");

			//Initialisation de l'animal
			final Animal animal = new Animal();
			animal.setNomA(nomA);
			animal.setSexe(sexe);
			animal.setType(typeAn);
			animal.setFonctionCage(fonctionCage);
			animal.setPays(pays);
			animal.setAnNais(annais);
			animaux.add(animal);

			//Récupération de la cage
			//@TODO : faire le read de CageDAO
			CageDAO cageDAO = new CageDAO(this.conn);
			animal.setLaCage(cageDAO.read(rs.getInt("NOCAGE")));



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
