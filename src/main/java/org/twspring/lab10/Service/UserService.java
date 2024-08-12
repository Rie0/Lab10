package org.twspring.lab10.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab10.Model.User;
import org.twspring.lab10.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //GET
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //POST
    public void addUser(User user) {
        userRepository.save(user);
    }
    //UPDATE
    public Boolean updateUser(Integer id, User user) {
        if(!userRepository.findById(id).isPresent()) {
            return false;
        }
        User updatedUser = userRepository.getById(id);
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAge(user.getAge());
        updatedUser.setRole(user.getRole());
        userRepository.save(updatedUser);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        if(!userRepository.findById(id).isPresent()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
