package com.example.repo.Repsitory;

import com.example.repo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findUserById(Integer id);

    @Query("select u from User u where u.email = ?1")
    User getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findUserByUsername(String username);

    //all user by specific role
    List<User>findUserByRole(String role);


    //spesifc age
    @Query("SELECT u FROM User u WHERE u.age >= ?1")
    List<User> findUserByAgeGreaterThanEqual(Integer age);




}
