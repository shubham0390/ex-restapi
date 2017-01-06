package com.km2labs.expenseview.rest.resources.auth;

import com.km2labs.expenseview.rest.dto.User;
import com.km2labs.expenseview.rest.dto.auth.LoginRequestDto;
import com.km2labs.expenseview.rest.dto.auth.LoginResponseDto;
import com.km2labs.expenseview.rest.dto.auth.SignupRequestDto;
import com.km2labs.expenseview.rest.dto.auth.SignupResponseDto;
import com.km2labs.expenseview.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static com.km2labs.expenseview.rest.ResponseGenrator.generateResponseWithTimestampDates;
import static com.km2labs.expenseview.rest.RestAPIUtil.buildErrorResponse;

@Component
@Path("/auth")
@Service
@Produces("application/json")
@Consumes("application/json")
public class AuthResource {

    private final AuthService mAuthService;

    @Autowired
    public AuthResource(AuthService mAuthService) {
        this.mAuthService = mAuthService;
    }

    @POST
    @Path("/register")
    public Response signup(SignupRequestDto signupRequestDto) {
        SignupResponseDto signupResponseDto = new SignupResponseDto();
        try {
            User user = mAuthService.register(signupRequestDto);
            signupResponseDto.setUser(user);
            return generateResponseWithTimestampDates(signupResponseDto);

        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @PUT
    @Path("/login")
    public Response login(LoginRequestDto loginRequestDto) {
        try {
            User user = mAuthService.login(loginRequestDto);
            return generateResponseWithTimestampDates(new LoginResponseDto(user));
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    @PUT
    @Path("/logout")
    public Response logout(String emailId) {
        throw new UnsupportedOperationException();
    }


}
