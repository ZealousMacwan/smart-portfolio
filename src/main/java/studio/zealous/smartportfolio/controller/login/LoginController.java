package studio.zealous.smartportfolio.controller.login;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.zealous.smartportfolio.dto.user.LoginRequestDTO;
import studio.zealous.smartportfolio.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<ObjectNode> login(@RequestBody LoginRequestDTO loginRequest){
        ObjectNode response = loginService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
