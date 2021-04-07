package be.willekens.template.security;

import be.willekens.template.security.users.Role;
import be.willekens.template.security.users.SecurityService;
import be.willekens.template.security.users.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EurderAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(EurderAuthenticationProvider.class);

    private final SecurityService securityService;

    public EurderAuthenticationProvider(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAccount user = securityService.getUser(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        if(user != null){
            logger.info("Succesfully logged in!");
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), rolesToGrantedAuthorities(user.getRoles()));
        }
        throw new BadCredentialsException("The provided credentials were invalid.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<String> roles) {
        return roles.stream()
                .flatMap(role -> Role.valueOf(role).getFeatureList().stream())
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
