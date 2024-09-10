package studio.zealous.smartportfolio.controller.login;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.zealous.smartportfolio.dto.user.LoginRequestDTO;
import studio.zealous.smartportfolio.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ObjectNode> login(@RequestBody LoginRequestDTO loginRequest){
        ObjectNode response = loginService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
