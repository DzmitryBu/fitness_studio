package by.it_academy.fitness.web.controllers;


import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.core.dto.UserLogin;
import by.it_academy.fitness.core.dto.UserRegistration;
import by.it_academy.fitness.service.api.IRegistrationService;
import by.it_academy.fitness.web.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class RegistrationController {
    private final IRegistrationService service;

    public RegistrationController(IRegistrationService service) {
            this.service = service;
    }

     @RequestMapping(path = "/registration", method = RequestMethod.POST)
     public ResponseEntity<?> addUser(@RequestBody UserRegistration user) {
         service.addUser(user);
         return ResponseEntity.status(HttpStatus.CREATED).build();
     }

     @RequestMapping(path = "/verification", method = RequestMethod.GET)
     public ResponseEntity<?> verification(@RequestParam("mail") String mail, @RequestParam("code") String code) {
         service.verification(mail, code);
         return ResponseEntity.status(HttpStatus.OK).build();
     }

     @RequestMapping(path = "/login", method = RequestMethod.POST)
     public String loging(@RequestBody UserLogin userLog) {
         User user = service.loging(userLog);

         return JwtTokenUtil.generateAccessToken(user);
//        return ResponseEntity.status(HttpStatus.OK).build();
     }


}
