package lt.ks.vtmc.serviceapi.mapper;

import lt.ks.vtmc.serviceapi.order.Order;
import lt.ks.vtmc.serviceapi.user.User;
import lt.ks.vtmc.serviceapi.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
//        List<UserDto.OrderDto> orders = user.getOrders().stream().map(this::toUserDtoOrderDto).toList();
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getRole()
//                , orders
        );
    }

    private UserDto.OrderDto toUserDtoOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        return new UserDto.OrderDto(order.getId(), order.getDescription(), order.getCreatedAt());
    }
}
