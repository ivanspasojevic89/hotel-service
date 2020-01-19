package com.someco.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Column(name = "UserID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "UserDisplayName")
    private String userDisplayName;

    @Column(name = "UserEmail")
    private String userEmail;

    @Column(name = "UserPassword")
    private String userPassword;

    @Column(name = "userRoleGroup")
    private String userRoleGroup;

    @Column(name = "UserCreated")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date userCreated;


}
