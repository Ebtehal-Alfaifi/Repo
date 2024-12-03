package com.example.repo.Service;

import com.example.repo.ApiResponse.ApiExeption;
import com.example.repo.Model.User;
import com.example.repo.Repsitory.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;


    public List<User> getAll() {
        return userRepo.findAll();
    }

    // قبل ما اضيف لازم اتأكد  اذا اليوزر موجود او لا
    public User addUser(User user) {
        if (userRepo.findUserByUsername(user.getUserName()) != null) {
            throw new ApiExeption("Username already exists");
        }

        if (userRepo.getUserByEmail(user.getEmail()) != null) {
            throw new ApiExeption("Email already exists");
        }

        return userRepo.save(user);
    }


    public User update(Integer id, User user) {
        User old = userRepo.findUserById(id);
        if (old == null) {
            throw new ApiExeption("user not found");
        }
        old.setUserName(user.getUserName());
        old.setEmail(user.getEmail());
        old.setPassword(user.getPassword());
        old.setAge(user.getAge());
        old.setRole(user.getRole());
        return userRepo.save(old);
    }


    public void delete(Integer id) {
        User user = userRepo.findUserById(id);
        if (user == null) {
            throw new ApiExeption(" user not found");
        }
        userRepo.delete(user);
    }

//check pass and user are correct
public void check(String username, String password) {
    User user = userRepo.findUserByUsername(username);
    if (user == null) {
        throw new ApiExeption("User not found with the username: " + username);
    }
    if (!user.getPassword().equals(password)) {
        throw new ApiExeption("Incorrect password for username: " + username);
    }}

    // get user by email
    public User getByEmail(String email) {
        User user = userRepo.getUserByEmail(email);
        if (user == null) {
            throw new ApiExeption(" email not found");
        }
        return user;

    }


    //get all user with role
    public List<User> getRole(String role) {
        List<User> users = userRepo.findUserByRole(role);
        if (users == null) {
            throw new ApiExeption("there is no user with that role");
        }
        return users;
    }


    //get user by age


    public List<User> getUsersByAge(Integer age) {
        List<User> users = userRepo.findUserByAgeGreaterThanEqual(age);
        if (users == null || users.isEmpty()) {
            throw new ApiExeption("There are no users with age greater than or equal to " + age);
        }
        return users;
    }





}


