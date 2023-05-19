package com.jareer.lms.app.domains;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class University {

    @Id
    private Integer id;
    private String name;
    private String address;
    private short openYear;

}
