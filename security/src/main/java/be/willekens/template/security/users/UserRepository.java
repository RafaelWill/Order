package be.willekens.template.security.users;

import be.willekens.template.infrastructure.exceptions.UsernameAlreadyExistsException;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;

@Repository
public class UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private final List<UserAccount> userAccounts = newArrayList(
            UserAccount.userAccount().withUsername("rafael").withPassword("admin").withRoles(Lists.newArrayList("ADMIN"))
    );

    public UserAccount getUser(String username, String password) {
        return userAccounts.stream()
                .filter(userAccount -> userAccount.getUsername().equals(username))
                .filter(userAccount -> userAccount.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void addUser(String username, String password) {
        if(usernameAlreadyExists(username)) {
            logger.warn("A user tried to register an username that is already in our system");
            throw new UsernameAlreadyExistsException("This username is already taken!");
        }
        userAccounts.add(new UserAccount().withUsername(username).withPassword(password).withRoles(Lists.newArrayList("CUSTOMER")));
    }

    private boolean usernameAlreadyExists(String username) {
        return userAccounts.stream().anyMatch(userAccount -> userAccount.getUsername().equals(username));
    }
}
