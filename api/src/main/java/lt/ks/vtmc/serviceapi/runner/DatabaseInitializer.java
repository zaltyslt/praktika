package lt.ks.vtmc.serviceapi.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.ks.vtmc.serviceapi.order.OrderRepository;
import lt.ks.vtmc.serviceapi.security.WebSecurityConfig;
import lt.ks.vtmc.serviceapi.service.AutoService;
import lt.ks.vtmc.serviceapi.service.ServiceRepository;
import lt.ks.vtmc.serviceapi.user.User;
import lt.ks.vtmc.serviceapi.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ServiceRepository serviceRepository;
    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN)
//            new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER)
    );

    private static final List<AutoService> AUTOSERVICES = Arrays.asList(
            new AutoService(null, "Service1", "Manager1", "Street1", "Vilnius", null),
            new AutoService(null, "Service2", "Manager2", "Street2", "Vilnius", null)

    );

    @Override
    public void run(String... args) {
        if (userService.getUsers().isEmpty()) {

            USERS.forEach(user -> {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.saveUser(user);
            });
        }
        if (serviceRepository.findAll().size() == 0) {
            serviceRepository.saveAll(AUTOSERVICES);
        }



        log.info("Database initialized");
    }

}
