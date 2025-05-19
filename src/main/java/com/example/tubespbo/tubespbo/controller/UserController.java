package com.example.tubespbo.tubespbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping(path="/api/users") 
public class UserController {
  // @Autowired 
         
  // private UserRepository userRepository;

  // @PostMapping(path="/add")
  // public String addNewUser (@RequestParam String username
  //     , @RequestParam String email) {

  //   UserEntity n = new UserEntity();
  //   n.setName(username);
  //   n.setEmail(email);
  //   userRepository.save(n);
  //   return "Saved";
  // }

  // @GetMapping(path="/all")
  // public Iterable<UserEntity> getAllUsers() {
  //   return userRepository.findAll();
  // }
}
