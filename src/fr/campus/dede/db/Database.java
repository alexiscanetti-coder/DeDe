package fr.campus.dede.db;
import fr.campus.dede.model.Characters;
import java.sql.*;
import java.util.*;

public class Database {
    private final String URL = "jdbc:mysql://localhost:3306/DeDe";
    private final String USER = "dede";
    private final String PASSWORD = "dungeon@73";

    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public List<Characters> fetchHeroes() {
        List<Characters> heroes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM characters");

            while (rs.next()) {
                Characters hero = new Characters(
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getInt("lifePoints"),
                        rs.getInt("strength"),
                        rs.getString("offensiveEquipment"),
                        rs.getString("defensiveEquipment")
                );
                hero.setId(rs.getInt("id"));
                heroes.add(hero);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return heroes;
    }

    public void createHero(Characters hero) {
        try {
            String sql = "INSERT INTO characters(type, name, lifePoints, strength, offensiveEquipment, defensiveEquipment) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, hero.getType());
            ps.setString(2, hero.getName());
            ps.setInt(3, hero.getLifePoints());
            ps.setInt(4, hero.getStrength());
            ps.setString(5, hero.getOffensiveEquipment());
            ps.setString(6, hero.getDefensiveEquipment());

            ps.executeUpdate();

            System.out.println(("Hero save !"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editHero(Characters hero) {
        try {
            String sql = "UPDATE characters SET type=?, name=?, lifePoints=?, strength=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, hero.getType());
            ps.setString(2, hero.getName());
            ps.setInt(3, hero.getLifePoints());
            ps.setInt(4, hero.getStrength());
            ps.setInt(5, hero.getId());

            ps.executeUpdate();

            System.out.println("Hero update !");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void changeLifePoints(Characters hero) {
        try {

            String sql = "UPDATE characters SET lifePoints=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, hero.getLifePoints());
            ps.setInt(2, hero.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
