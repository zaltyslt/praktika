package lt.ks.vtmc.serviceapi.user;

import java.time.ZonedDateTime;

public record UserDto(Long id, String username, String name, String email, String role
//,                      List<OrderDto> orders
) {

    public record OrderDto(String id, String description, ZonedDateTime createdAt) {
    }
}