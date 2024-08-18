package studio.zealous.smartportfolio.controller.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.zealous.smartportfolio.dto.ApiResponse;
import studio.zealous.smartportfolio.dto.user.SignUpRequestDTO;
import studio.zealous.smartportfolio.entity.User;
import studio.zealous.smartportfolio.service.UserService;
import studio.zealous.smartportfolio.util.time.TimeUtil;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setAngelUserId(signUpRequestDTO.getAngelUserId());
        user.setUserName(signUpRequestDTO.getUserName());
        user.setEmailId(signUpRequestDTO.getEmailId());
        user.setPassword(signUpRequestDTO.getPassword());
        user.setCreatedAt(TimeUtil.nowWithZone());
        user.setUpdatedAt(TimeUtil.nowWithZone());
        userService.saveUser(user);
        ApiResponse apiResponse = new ApiResponse(true, "Signup successful", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
