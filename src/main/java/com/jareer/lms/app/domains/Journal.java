package com.jareer.lms.app.domains;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Journal {

    @Id
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


}
