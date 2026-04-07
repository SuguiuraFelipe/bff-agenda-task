package com.suguiura.bffagenda.controller;



import com.suguiura.bffagenda.business.UserService;
import com.suguiura.bffagenda.business.dto.in.AddressDTORequest;
import com.suguiura.bffagenda.business.dto.in.LoginDTORequest;
import com.suguiura.bffagenda.business.dto.in.PhoneNumberDTORequest;
import com.suguiura.bffagenda.business.dto.in.UserDTORequest;
import com.suguiura.bffagenda.business.dto.out.AddressDTOResponse;
import com.suguiura.bffagenda.business.dto.out.PhoneNumberDTOResponse;
import com.suguiura.bffagenda.business.dto.out.UserDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Register and login to users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Save Users", description = "Create a new User")
    @ApiResponse(responseCode = "200", description = "User save with success")
    @ApiResponse(responseCode = "409", description = "User already register")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTOResponse> saveUSer(@RequestBody UserDTORequest userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Users", description = "Login user")
    @ApiResponse(responseCode = "200", description = "Logged in user")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Server error")
    public String login(@RequestBody LoginDTORequest userDTO) {
        return userService.userLogin(userDTO);
    }

    @GetMapping
    @Operation(summary = "Fetch user data by email", description = "Fetch user data")
    @ApiResponse(responseCode = "200", description = "found user")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "not found user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTOResponse> findUserByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token){ //Requisição via parametro
        return ResponseEntity.ok(userService.findUserByEmail(email, token)); // ResponseEntity.ok serve para formatar a resposta de maneira correta para o response HTTP
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by email", description = "Delete user data")
    @ApiResponse(responseCode = "200", description = "deleted user")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "not found user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build(); // garantir o retorno em caso de erro
    }

    @PutMapping
    @Operation(summary = "Update user data", description = "update user data")
    @ApiResponse(responseCode = "200", description = "updated user")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "not found user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<UserDTOResponse> updateUserData(@RequestBody UserDTORequest dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateUserData(token, dto));
    }

    @PutMapping("/address")
    @Operation(summary = "Update user address data", description = "update user data")
    @ApiResponse(responseCode = "200", description = "updated user address")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "not found user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestBody AddressDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateAddress(id, dto, token));
    }

    @PutMapping("/phone")
    @Operation(summary = "Update user phone data", description = "update user data")
    @ApiResponse(responseCode = "200", description = "updated user phone")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "not found user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneNumberDTOResponse> updatePhoneNumber(@RequestBody PhoneNumberDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updatePhoneNumber(id, dto, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Register user address data", description = "Register user data")
    @ApiResponse(responseCode = "200", description = "Registered user address")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "not found user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<AddressDTOResponse> registerAddress(@RequestBody AddressDTORequest dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.registerAddress(token, dto));
    }

    @PostMapping("/phone")
    @Operation(summary = "Register user phone data", description = "Register user data")
    @ApiResponse(responseCode = "200", description = "Register user phone")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "403", description = "not found user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<PhoneNumberDTOResponse> registerAddress(@RequestBody PhoneNumberDTORequest dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.registerPhoneNumber(token, dto));
    }
}
