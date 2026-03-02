package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Person {

    private String firstName;
    private String lastName;
    private String postCode;

    public static Person decode(Map<String, String> row) {

        return new Person(
                row.get("firstName"),
                row.get("lastName"),
        row.get("postCode"));
    }
}
