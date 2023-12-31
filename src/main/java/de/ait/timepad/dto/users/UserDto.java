package de.ait.timepad.dto.users;

import de.ait.timepad.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "System User")
public class UserDto {

    @Schema(description = "user's id", example = "1")
    private Long id;

    @Schema(description = "user's email", example = "example@mail.com")
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Schema(description = "user's role - ADMIN, USER, MANAGER", example = "ADMIN")
    @NotNull
    @NotBlank
    private String role;

    @Schema(description = "user's state - NOT_CONFIRMED, CONFIRMED, BANNED, DELETED", example = "CONFIRMED")
    @NotNull
    @NotBlank
    private String state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .state(user.getState().name())
                .role(user.getRole().name())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
