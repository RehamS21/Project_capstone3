package com.example.labority_project.Service;

import com.example.labority_project.Api.ApiException;
import com.example.labority_project.Model.Laboratories;
import com.example.labority_project.Model.TestType;
import com.example.labority_project.Model.User;
import com.example.labority_project.Repository.LaboartoriesRepository;
import com.example.labority_project.Repository.TestTypeRepository;
import com.example.labority_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LaboartoriesRepository laboartoriesRepository;
    private final TestTypeRepository testTypeRepository;

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }


    public void addUser(User user){


        userRepository.save(user);
    }



    public void updateUser(Integer id, User user){
        User oldUser=userRepository.findUserById(id);
        if(oldUser==null){
            throw new ApiException("id not found");
        }


        oldUser.setName(user.getName());
        oldUser.setBirth_date(user.getBirth_date());
        oldUser.setBalance(user.getBalance());
        oldUser.setAge(user.getAge());
        oldUser.setGender(user.getGender());
        userRepository.save(oldUser);

    }
    public void deleteUser(Integer id){
        User user=userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("id not found");
        }


        userRepository.deleteById(id);
    }



//    public void assignUserToLaboratory(Integer laboratory_id , Integer user_id){
//        Laboratories laboratories = laboartoriesRepository.findLaboratoriesById(laboratory_id);
//        User user = userRepository.findUserById(user_id);
//
//        if (laboratories == null)
//            throw new ApiException("Sorry, the laboratory not found");
//        else if (user == null)
//            throw new ApiException("Sorry , the user nor found");
//
//        laboratories.getUsers().add(user);
//        user.getLaboratories().add(laboratories);
//
//        laboartoriesRepository.save(laboratories);
//        userRepository.save(user);
//    }

}
