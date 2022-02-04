package fr.litopia.dao;

import fr.litopia.modele.Cage;
import fr.litopia.modele.Gardien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class CageDAO extends DAO<Cage> {

	public CageDAO(Connection conn){
		super(conn);
	}
	
	
	@Override
	public boolean create(Cage obj) throws SQLException{
		return false;
	}
	
	@Override
	public Cage read(Object obj) throws SQLException {
		Integer noCage = (Integer) obj;
		String sql = "SELECT FONCTION, NOALLEE FROM LESCAGES WHERE NOCAGE = ?";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1, noCage);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String fonction = rs.getString("FONCTION");
			Integer noAllee = rs.getInt("NOALLEE");
			Cage cage = new Cage(noCage);
			cage.setFonction(fonction);
			cage.setNoAlle(noAllee);
			return cage;
		}
		return null;
	}
	
	public Set<Cage> readByFonction(String fonction) throws SQLException {
		Set<Cage> cages = new HashSet<>();

                /* TO DO */

		return cages;
	}

	public Set<Cage> readByGardien(Gardien g) throws SQLException {
		Set<Cage> cages = new HashSet<>();

                /* TO DO */

		return cages;
	}
	
	@Override
	public Set<Cage> readAll() throws SQLException {
		Set<Cage> cages = new HashSet<>();
		String sql = "SELECT * FROM LESCAGES";
		ResultSet rs = this.conn.prepareStatement(sql).executeQuery();
		while (rs.next()) {
			Integer noCage = rs.getInt("NOCAGE");
			String fonctionCage = rs.getString("FONCTION");
			Integer noAllee = rs.getInt("NOALLEE");
			Cage cage = new Cage(noCage);
			cage.setFonction(fonctionCage);
			cage.setNoAlle(noAllee);
			cages.add(cage);
		}
		return cages;
	}

	@Override
	public boolean update(Cage cage) throws SQLException{
		return false;
	}

	@Override
	public boolean delete(Cage obj) throws SQLException {
		return false;
	}



}
