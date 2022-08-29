package ro.robert.ducklin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification {

    private String subject;
    private String body;
    private String recipient;
}
