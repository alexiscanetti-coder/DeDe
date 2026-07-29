package fr.campus.dede.db;

import fr.campus.dede.items.OffensiveEquipment;
import fr.campus.dede.items.equipment.Spell;
import fr.campus.dede.items.equipment.Weapon;

import java.sql.*;

public class EquipmentDAO {

    private final Connection connection;

    public EquipmentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public OffensiveEquipment fetchById(int id) {
        try {
            String sql = "SELECT * FROM equipments WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String category = rs.getString("category");
                String type = rs.getString("type");
                int damage = rs.getInt("damage");

                return "WEAPON".equals(category)
                        ? new Weapon(type, damage)
                        : new Spell(type, damage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int findIdByEquipment(OffensiveEquipment equipment) {
        try {
            String sql = "SELECT id FROM equipments WHERE type = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, equipment.getType());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}