package gmail.vezhur2003.blps.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionData {
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    @Size(min = 1, max = 255, message = "не может быть более 255 символов")
    @Email
    private String email;
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    private String tag;

    public SubscriptionData(String email, String tag) {
        this.email = email;
        this.tag = tag;
    }
}
