package be.willekens.template.security.users;

import be.willekens.template.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityService {

    private final Map<String, String> usersToCustomers;
    private final UserRepository userRepository;
    private final OrderService orderService;

    public SecurityService(UserRepository userRepository, OrderService orderService) {
        this.usersToCustomers = init();
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    private Map<String, String> init() {
        Map<String, String> map = new HashMap<>();
        map.put("rafael","d774eceb-03c5-4f63-9e92-aa1025b2257f");
        return map;
    }

    public void addUserAndCustomerId(String username, String customerId) {
        usersToCustomers.put(username,customerId);
    }

    public String getCustomerIdOfUser(Authentication authentication) {
        return usersToCustomers.get(authentication.getName());
    }

    public boolean canAccessId(Authentication authentication, String customerId) {
        return getCustomerIdOfUser(authentication).equals(customerId);
    }

    public UserAccount getUser(String username, String password) {
        return userRepository.getUser(username, password);
    }

    public void addUser(String username, String password) {
        userRepository.addUser(username,password);
    }

    public boolean canAccessOrderId(Authentication authentication, String orderId) {
        return orderService.getOrderById(orderId).getCustomerId().equals(getCustomerIdOfUser(authentication));
    }
}
