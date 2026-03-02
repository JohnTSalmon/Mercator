package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Login {

    private String user;
    private String password;

    public static Login decode(Map<String, String> row) {

        return new Login(
                row.get("user"),
                row.get("password"));
    }
}
