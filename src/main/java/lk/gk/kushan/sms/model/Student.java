package lk.gk.kushan.sms.model;

import lk.gk.kushan.sms.model.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
    private LocalDate dateOfBirth;

}
