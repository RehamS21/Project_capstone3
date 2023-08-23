package com.example.labority_project.Service;


import com.example.labority_project.Api.ApiException;
import com.example.labority_project.DTO.UserDto;
import com.example.labority_project.Model.User;
import com.example.labority_project.Model.UserDetails;
import com.example.labority_project.Repository.UserDetailsRepository;
import com.example.labority_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;


    public void addUserDetails(UserDto userDto){
        User user=userRepository.findUserById(userDto.getUser_id());
        if (user == null){
            throw new ApiException("id is null");
        }


        if(userDto.getIsIllness().equals(false)){
            userDto.setIllness_type(null);
        }

        UserDetails userDetails =new UserDetails(null, userDto.getBlood_type(),userDto.getIsIllness(),userDto.getIllness_type(),user);
        userDetailsRepository.save(userDetails);
    }

    public List<UserDetails> getAllUsersDetails(){

        return userDetailsRepository.findAll();
    }


    public void updateUserDetails(Integer id,UserDto userDto){
        UserDetails userDetails =userDetailsRepository.findUserDetailsById(id);
        if(userDetails ==null){
            throw new ApiException("id not found");
        }
        if(userDto.getIsIllness().equals(false)){
            userDto.setIllness_type(null);
        }

        userDetails.setBlood_type(userDto.getBlood_type());
        userDetails.setIsIllness(userDto.getIsIllness());
        userDetails.setIllness_type(userDto.getIllness_type());
        userDetailsRepository.save(userDetails);

    }
    public void deleteUserDetails(Integer id){

        UserDetails userDetails =userDetailsRepository.findUserDetailsById(id);
        User user=userRepository.findUserById(id);

        if(userDetails == null || user == null){
            throw new ApiException("id not found");
        }
        userDetailsRepository.deleteAllByIdInBatch(Collections.singleton(id));
    }

    public void assignUserToUserDetails(Integer user_id,Integer userDetails_id){

        User user=userRepository.findUserById(user_id);
        UserDetails details =userDetailsRepository.findUserDetailsById(userDetails_id);

        if (details ==null || user==null){
            throw  new ApiException("Can not Assign ids could be wrong");
        }
        details.setUser(user);


    }

}
