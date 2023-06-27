package lt.ks.vtmc.serviceapi.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.ks.vtmc.serviceapi.config.SwaggerConfig;
import lt.ks.vtmc.serviceapi.mapper.UserMapper;
import lt.ks.vtmc.serviceapi.security.CustomUserDetails;
import lt.ks.vtmc.serviceapi.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final UserService userService;
//    private final UserMapper userMapper;
    private final ServicesService servicesService;

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public AutoService createService(@AuthenticationPrincipal CustomUserDetails currentUser,
                                     @Valid @RequestBody AutoService newService) {
        AutoService autoService = servicesService.createService(newService);
        return  new AutoService();
    }

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<AutoService> getServices(@AuthenticationPrincipal CustomUserDetails currentUser,
                                         @RequestParam(value = "text", required = false) String text) {
        return  (text == null)
                ? servicesService.getServices()
                :  servicesService.getService(text);
    }

    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping ("/delete")
    public void deleteService(@AuthenticationPrincipal CustomUserDetails currentUser,
//                                          @Valid @RequestBody Long menuToDeleteId
                                 @RequestParam(value = "id", required = true) Long serviceId ) {
        servicesService.deleteService(serviceId);
    }

//    public List<ClientDto> getClients(@RequestParam(value = "text", required = false) String text) {
//        List<Client> clients = (text == null)
//                ? clientService.getClients()
//                :  clientService.getClient();
//
//        return clients.stream()
//                .map(clientMapper::toClientDto)
//                .toList();
//    }
//
//    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BEARER_KEY_SECURITY_SCHEME)})
//    @GetMapping
//    public List<UserDto> getUsers() {
//        return userService.getUsers().stream()
//                .map(userMapper::toUserDto)
//                .collect(Collectors.toList());
//    }
//
//    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BEARER_KEY_SECURITY_SCHEME)})
//    @GetMapping("/{username}")
//    public UserDto getUser(@PathVariable String username) {
//
//        var aa = userMapper.toUserDto(userService.validateAndGetUserByUsername(username));
//        return aa;
//    }
//
//    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BEARER_KEY_SECURITY_SCHEME)})
//    @DeleteMapping("/{username}")
//    public UserDto deleteUser(@PathVariable String username) {
//        User user = userService.validateAndGetUserByUsername(username);
//        userService.deleteUser(user);
//        return userMapper.toUserDto(user);
//    }
}
