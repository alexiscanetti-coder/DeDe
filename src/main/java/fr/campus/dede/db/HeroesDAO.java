package fr.campus.dede.db;

import fr.campus.dede.model.Heroes;
import fr.campus.dede.model.heroes.*;

import java.sql.*;
import java.util.*;

public class HeroesDAO {

    private final Connection connection;
    private final EquipmentDAO equipmentDAO;

    public HeroesDAO() {
        this.connection = DatabaseConnection.getConnection();
        this.equipmentDAO = new EquipmentDAO();
    }

    public List<Heroes> fetchAll() {
        List<Heroes> heroes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM heroes");
            while (rs.next()) {
                heroes.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroes;
    }

    private Heroes mapRow(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        String name = rs.getString("name");
        int lifePoints = rs.getInt("life_points");
        int strength = rs.getInt("strength");
        String offEquip = rs.getString("offensive_equipment_id");
        String defEquip = rs.getString("defensive_equipment_id");

        Heroes hero;

        switch (type) {
            case "WARRIOR":
                hero = new Warrior(name, null, null);
                break;
            case "WIZARD":
                hero = new Wizard(name, null, null);
                break;
            default:
                throw new IllegalArgumentException("Type inconnu : " + type);
        }

        hero.setId(rs.getInt("id"));
        hero.setMaxLifePoints(lifePoints);
        hero.setLifePoints(lifePoints);
        hero.setStrength(strength);
        return hero;
    }

    public void create(Heroes hero) {
        try {
            String sql = "INSERT INTO heroes(type, name, life_points, strength, offensive_equipment_id, defensive_equipment_id) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, hero.getType());
            ps.setString(2, hero.getName());
            ps.setInt(3, hero.getLifePoints());
            ps.setInt(4, hero.getStrength());

            int offId = equipmentDAO.findIdByEquipment(hero.getOffensiveEquipment());
            if (offId > 0) {
                ps.setInt(5, offId);
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Heroes hero) {
        try {
            String sql = "UPDATE heroes SET type=?, name=?, life_points=?, strength=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, hero.getType());
            ps.setString(2, hero.getName());
            ps.setInt(3, hero.getLifePoints());
            ps.setInt(4, hero.getStrength());
            ps.setInt(5, hero.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Heroes hero) {
        try {
            String sql = "DELETE FROM heroes WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, hero.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}