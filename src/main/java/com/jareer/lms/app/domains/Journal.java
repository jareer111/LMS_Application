package com.jareer.lms.app.domains;


import com.jareer.lms.app.domains.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "group_id"})})
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> students;


    @OneToOne
    private Group group;
    private boolean deleted;
}
