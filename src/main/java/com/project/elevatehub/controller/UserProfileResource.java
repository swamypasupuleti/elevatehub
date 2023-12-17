package com.project.elevatehub.controller;


import com.project.elevatehub.constants.FileConstants;
import com.project.elevatehub.constants.Status;
import com.project.elevatehub.dto.AuthRequest;
import com.project.elevatehub.exception.DataValidationException;
import com.project.elevatehub.model.ApiResponseModel;
import com.project.elevatehub.model.Users;
import com.project.elevatehub.repository.UsersRepository;
import com.project.elevatehub.service.JwtService;
import com.project.elevatehub.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserProfileResource {

    private static final Logger log = LoggerFactory.getLogger(UserProfileResource.class);
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public Map<String, Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Map<String, Object> map = new HashMap<String, Object>();
        Users userInfo = usersRepository.findByUserNameAndPassword(authRequest.getUsername(), authRequest.getPassword());
        if (userInfo != null) {
            String token = jwtService.generateToken(authRequest.getUsername());
            map.put("token", "Bearer " + token);
            map.put("user",userInfo);
            return map;
        } else {
            throw new DataValidationException("invalid user request !");
        }
    }


    @GetMapping()
    @Produces("application/json")
    public ResponseEntity<ApiResponseModel> getAll() {
        try {
            return ResponseEntity.ok().body(new ApiResponseModel(Status.SUCCESS, usersRepository.findAll()));
        } catch (Exception e) {
            ApiResponseModel response = new ApiResponseModel(Status.ERROR, "Internal Service Error Occurred. Try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/velocity")
    public  ResponseEntity<ApiResponseModel> getVelocityByFiltration(HttpServletRequest request, @RequestParam(value = "duration",required = false) String duration,
                                                @RequestParam(value = "employeeId",required = false) String employeeId,
                                                @RequestParam(value = "projectCode",required = false) String projectCode){

        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }
            if(duration == null){
                duration = FileConstants.LAST_2_MONTHS;
            }
            if(employeeId == null){
                employeeId = username;
            }
            return ResponseEntity.ok().body(new ApiResponseModel(Status.SUCCESS, userService.getVelocityByFilterType(duration, employeeId, projectCode)));
        } catch(ExpiredJwtException expiredJwtException){
            log.error("User Token is Expired ", expiredJwtException);
            ApiResponseModel response = new ApiResponseModel(Status.ERROR, "User Token is Expired. please login Again.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        catch (Exception e) {
            log.error("User Id={}, Exception occurred in Get UserProfile by ID: ", e);
            ApiResponseModel response = new ApiResponseModel(Status.ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
