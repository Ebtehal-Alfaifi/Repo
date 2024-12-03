package com.example.repo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role IN ('admin', 'user')")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = " name can not be null")
    @Size(min = 5,max = 10,message = " your name at least should  has 5 character ")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;


    @NotEmpty(message = "  user name can not be null")
    @Size(min = 5,max = 10,message = " your  user name at least should  has 5 character ")
@Column(columnDefinition = "varchar(10) not null unique")
    private String userName;


    @NotEmpty(message = " password can not be null")
    @Size(min = 8,max = 10,message = " you can not enter password less than 8 or more than 10 ")
    @Column(columnDefinition = "varchar(10) not null")
    private String password;


    @NotEmpty(message = " email can not be empty")
    @Email(message = " you should enter valid email")
    @Column(columnDefinition = "varchar(20) not null unique ")

    private String email;

    @NotEmpty(message = " role can not be empty")
    @Column(columnDefinition = "varchar(5) not null")
    @Pattern(regexp = "^(?i)(admin|user)$",message = " only user or admin allow to enter")
    private String role;

    @NotNull(message = " age can not be null")
    @Min(15)// we use it for integer only
    @Column(columnDefinition = "int not null")
    private Integer age;
}
