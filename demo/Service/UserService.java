package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addAllUsers(User user){
        userRepository.save(user);
    }
    public Boolean updateUser(Integer id,User user){
        User oldUser=userRepository.getById(id);
        if (oldUser==null){
            return false;
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id){
        User user= userRepository.getById(id);
        if (user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public List<User> getUserStatistics(Integer id) {
        User user = userRepository.getById(id);
        if (user==null){
            return null;
        }
        user.getUsername();
        user.getPurchaseCount();
        user.getTotalSpent();
        return userRepository.findAll();
    }


    public User getUserByRole(String role,Integer id) {
        User user = userRepository.getById(id);
            if (user.getId().equals(role)) {
                return user;

        }
        return null;
    }
}
