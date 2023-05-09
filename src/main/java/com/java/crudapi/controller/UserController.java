package com.java.crudapi.controller;

import com.java.crudapi.config.JwtTokenUtil;
import com.java.crudapi.exception.ResourceNotFoundException;
import com.java.crudapi.model.ApiResponse;
import com.java.crudapi.model.User;
import com.java.crudapi.model.UserResponse;
import com.java.crudapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.java.crudapi.model.ApiResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String token = null;

    @PostMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody User user, HttpServletRequest request) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("El correo electrónico ya está en uso"));
        }

        if (!isValidPassword(user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("La contraseña debe tener al menos una mayúscula, una minúscula y dos números"));
        }

        user.setCreated(new Date());
        user.setModified(new Date());
        final User finalUser = user;
        user.getPhones().forEach(phone -> phone.setUser(finalUser));
        user = userRepository.save(user);  //Acá lo inserta


        //final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getEmail());
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        // Construyendo UserResponse con el token generado
        UserResponse userResponse = new UserResponse(user.getId(), user.getCreated(), user.getModified(), token);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "mensaje de ok", userResponse));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + id));

        String token = jwtTokenUtil.generateToken(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>()));

        UserResponse userResponse = new UserResponse(user.getId(), user.getCreated(), user.getModified(), token);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "mensaje de ok", userResponse));
    }



    @GetMapping("")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = users.stream()
                .map(user -> {
                    String token = jwtTokenUtil.generateToken(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                            new ArrayList<>()));
                    return new UserResponse(user.getId(), user.getCreated(), user.getModified(), token);
                })
                .collect(Collectors.toList());

        String message="";
        if(userResponses.stream().count()==0)
        {
            message = "no hay usuarios";
        }
        else
        {
            message = "mensaje de ok";
        }
        return ResponseEntity.ok(new ApiResponse<List<UserResponse>>(HttpStatus.OK.value(), message, userResponses));
    }



    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable(value = "id") Long userId,
                                                                @Valid @RequestBody User updateUser, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7); // Quita la palabra 'Bearer ' del token


        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        // Actualiza los campos del usuario existente
        userToUpdate.setName(updateUser.getName());
        userToUpdate.setEmail(updateUser.getEmail());
        userToUpdate.setPassword(updateUser.getPassword());
        userToUpdate.setModified(new Date());
        userToUpdate.setPhones(updateUser.getPhones());

        final User finalUserToUpdate = userToUpdate;
        userToUpdate.getPhones().forEach(phone -> phone.setUser(finalUserToUpdate));
        User updatedUser = userRepository.save(userToUpdate);

        // Construye el objeto UserResponse con la información actualizada del usuario
        UserResponse userResponse = new UserResponse(updatedUser.getId(), updatedUser.getCreated(),
                updatedUser.getModified(), null);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), null, userResponse));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + id));

        userRepository.delete(user);

        return ResponseEntity.ok(new ApiResponse<String>(HttpStatus.OK.value(), "Usuario eliminado correctamente.", null));
    }



    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9]{2})(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        return password.matches(regex);
    }
}