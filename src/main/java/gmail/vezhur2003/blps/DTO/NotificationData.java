package gmail.vezhur2003.blps.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class NotificationData {
    private String email;
    private String tag;
    private String name;

    public NotificationData(String email, String tag, String name) {
        this.email = email;
        this.tag = tag;
        this.name = name;
    }
}
