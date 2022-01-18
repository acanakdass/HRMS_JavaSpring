package javaProjects.HRMS.core.Security.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import javaProjects.HRMS.core.business.abstracts.UserService;
import javaProjects.HRMS.core.business.concretes.UserManager;
import javaProjects.HRMS.core.utilities.security.Helpers.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is : {}",username);
        log.info("Password is : {}",password);

        /*var employerRole = userService.getRoleByName("employer_role").getData();
        var isEmployerRole = userService.getByEmail(username).getData().getRoles().contains(employerRole);
        if (isEmployerRole){
            log.info("This is an employer user");
        }
        log.info("Not an employer");*/

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        log.info(authenticationToken.toString());
        var authRes = authenticationManager.authenticate(authenticationToken);;
        return authRes;


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();
        log.info("successful authentication");
        TokenHelper tokenHelper = new TokenHelper();
        String accessToken = tokenHelper.createToken(user,60,request.getRequestURL().toString());
        String refreshToken = tokenHelper.createToken(user,60,request.getRequestURL().toString());

        Map<String,String> tokens = new HashMap<>();

        tokens.put("accesToken",accessToken);
        tokens.put("refreshToken",refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp",Calendar.getInstance().getTime());
        data.put("message",exception.getMessage());
        log.error(exception.getMessage());
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }

}
