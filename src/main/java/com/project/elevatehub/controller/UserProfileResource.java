package com.project.elevatehub.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.elevatehub.constants.Status;
import com.project.elevatehub.exception.DataValidationException;
import com.project.elevatehub.model.ApiResponseModel;
import com.project.elevatehub.model.entities.Users;
import com.project.elevatehub.repository.UsersRepository;
import com.project.elevatehub.service.UserProfileService;
import com.project.elevatehub.util.Validator;

import javassist.NotFoundException;

@RestController
@RequestMapping("/users")
public class UserProfileResource {

    private static final Logger log = LoggerFactory.getLogger(UserProfileResource.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private Validator validator;

    @GetMapping
    @Produces("application/json")
    public ResponseEntity<ApiResponseModel> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        ApiResponseModel response = new ApiResponseModel(Status.SUCCESS, users);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseEntity<ApiResponseModel> addUserProfile(@Valid @RequestBody Users user) {
        try {
            validator.validatePhoneNumber(user.getPhoneNumber());
            userProfileService.enrichUserData(user);
            log.info("Recieved User data: {}", user);
            Users storedUser = usersRepository.save(user);
            ApiResponseModel response = new ApiResponseModel(Status.SUCCESS, storedUser);
            return ResponseEntity.ok().body(response);
        } catch (DataValidationException e) {
            String errorMsg = e.getMessage();
            log.error("UserId={}, {}", user.getId(), errorMsg);
            ApiResponseModel response = new ApiResponseModel(Status.FAILED, errorMsg);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            log.error("Data Recieved={}, Exception occurred in Add UserProfile:", user, e);
            ApiResponseModel response = new ApiResponseModel(Status.ERROR, "Internal Service Error Occurred. Try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    @Produces("application/json")
    public ResponseEntity<ApiResponseModel> getUserById(@PathVariable Long id) {
        try {
            log.debug("User Id: {} details requested", id);
            Optional<Users> opUser = usersRepository.findById(id);
            return opUser.map(user -> ResponseEntity.ok().body(new ApiResponseModel(Status.SUCCESS, user)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("User Id={}, Exception occurred in Get UserProfile by ID: ", id, e);
            ApiResponseModel response = new ApiResponseModel(Status.ERROR, "Internal Service Error Occurred. Try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    @Consumes("application/json")
    public ResponseEntity<ApiResponseModel> updateUserProfile(@RequestBody Users user, @PathVariable Long id) {
        try {
            validator.userDetailsUpdate(user);
            validator.validateUserId(user.getId());
            //TODO: User Input Validation
            userProfileService.updateUserDetails(id, user);
            return ResponseEntity.accepted().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DataValidationException e) {
            String errorMsg = e.getMessage();      
            log.error("Data Recieved={}, {}", user, errorMsg);
            ApiResponseModel response = new ApiResponseModel(Status.FAILED, errorMsg);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            log.error("Data Recieved={}, Exception occurred in UpdateUserProfile: ", user, e);
            ApiResponseModel response = new ApiResponseModel(Status.ERROR, "Internal Service Error Occurred. Try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseModel> handleValidationExceptions(MethodArgumentNotValidException e) {

        /*Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        ApiResponseModel response = new ApiResponseModel(Status.FAILED, errors);*/

        String error = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ApiResponseModel response = new ApiResponseModel(Status.FAILED, error);
        return ResponseEntity.badRequest().body(response);
    }

}
