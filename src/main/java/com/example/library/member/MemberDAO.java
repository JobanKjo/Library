package com.example.library.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;


public class MemberDAO {
    private Connection connection;

   
    public MemberDAO(Connection connection) {
        this.connection = connection;
    }

    
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Members";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int memberID = resultSet.getInt("MemberID");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String email = resultSet.getString("Email");
            String phoneNumber = resultSet.getString("PhoneNumber");
            String address = resultSet.getString("Address");
            members.add(new Member(memberID, firstName, lastName, email, phoneNumber, address));
        }
        return members;
    }

    
    public int addMember(Member member) throws SQLException {
        String query = "INSERT INTO Members (FirstName, LastName, Email, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, member.getFirstName());
        statement.setString(2, member.getLastName());
        statement.setString(3, member.getEmail());
        statement.setString(4, member.getPhoneNumber());
        statement.setString(5, member.getAddress());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("Creating member failed, no ID obtained.");
        }
    }

   
    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE Members SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ? WHERE MemberID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, member.getFirstName());
        statement.setString(2, member.getLastName());
        statement.setString(3, member.getEmail());
        statement.setString(4, member.getPhoneNumber());
        statement.setString(5, member.getAddress());
        statement.setInt(6, member.getMemberID());
        statement.executeUpdate();
    }

    
    public void deleteMember(int memberID) throws SQLException {
        String query = "DELETE FROM Members WHERE MemberID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, memberID);
        statement.executeUpdate();
    }
    
    public boolean memberExists(int memberID) throws SQLException {
        String query = "SELECT * FROM Members WHERE MemberID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, memberID);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next(); 
    }
    
    
    
}

