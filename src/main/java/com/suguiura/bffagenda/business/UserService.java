package com.suguiura.bffagenda.business;

import com.suguiura.bffagenda.business.dto.in.AddressDTORequest;
import com.suguiura.bffagenda.business.dto.in.LoginDTORequest;
import com.suguiura.bffagenda.business.dto.in.PhoneNumberDTORequest;
import com.suguiura.bffagenda.business.dto.in.UserDTORequest;
import com.suguiura.bffagenda.business.dto.out.AddressDTOResponse;
import com.suguiura.bffagenda.business.dto.out.PhoneNumberDTOResponse;
import com.suguiura.bffagenda.business.dto.out.UserDTOResponse;
import com.suguiura.bffagenda.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient client;

    public UserDTOResponse saveUser(UserDTORequest userDTO){
        return client.saveUSer(userDTO);
    }

    public String userLogin(LoginDTORequest userDTO){
        return client.login(userDTO);
    }

    public UserDTOResponse findUserByEmail(String email, String token) {
        return client.findUserByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token){
        client.deleteUserByEmail(email, token);
    }

    public UserDTOResponse updateUserData(String token, UserDTORequest dto){
        return client.updateUserData(dto, token);
    }

    public AddressDTOResponse updateAddress(Long id, AddressDTORequest addressDTORequest, String token){
        return client.updateAddress(addressDTORequest, id, token);
    }

    public PhoneNumberDTOResponse updatePhoneNumber(Long id, PhoneNumberDTORequest dto, String token){
        return client.updatePhoneNumber(dto, id, token);
    }

    public AddressDTOResponse registerAddress(String token, AddressDTORequest dto){
        return client.registerAddress(dto, token);
    }

    public PhoneNumberDTOResponse registerPhoneNumber(String token, PhoneNumberDTORequest dto){
        return client.registerPhoneNumber(dto, token);
    }
}
