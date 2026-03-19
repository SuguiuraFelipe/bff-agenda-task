package com.suguiura.bffagenda.infrastructure.client;

import com.suguiura.bffagenda.business.dto.in.AddressDTORequest;
import com.suguiura.bffagenda.business.dto.in.LoginDTORequest;
import com.suguiura.bffagenda.business.dto.in.PhoneNumberDTORequest;
import com.suguiura.bffagenda.business.dto.in.UserDTORequest;
import com.suguiura.bffagenda.business.dto.out.AddressDTOResponse;
import com.suguiura.bffagenda.business.dto.out.PhoneNumberDTOResponse;
import com.suguiura.bffagenda.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTOResponse findUserByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTOResponse saveUSer(@RequestBody UserDTORequest userDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest userDTO);

    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTOResponse updateUserData(@RequestBody UserDTORequest dto, @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PutMapping("/phone")
    PhoneNumberDTOResponse updatePhoneNumber(@RequestBody PhoneNumberDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PostMapping("/address")
    AddressDTOResponse registerAddress(@RequestBody AddressDTORequest dto, @RequestHeader("Authorization") String token);

    @PostMapping("/phone")
    PhoneNumberDTOResponse registerPhoneNumber(@RequestBody PhoneNumberDTORequest dto, @RequestHeader("Authorization") String token);
}
