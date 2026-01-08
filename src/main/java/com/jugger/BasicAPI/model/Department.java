package com.jugger.BasicAPI.model;

import jakarta.persistence.*;
import lombok.Data;

// import java.util.List;

// import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    @Column(nullable = false)
    private String departmentName;
}

