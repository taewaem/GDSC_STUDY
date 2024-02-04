package hello.gdsc.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Telephone
    private String phoneNumber;


}
