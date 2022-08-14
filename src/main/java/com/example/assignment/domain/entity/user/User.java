package com.example.assignment.domain.entity.user;

import com.example.assignment.domain.entity.novel.Novel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Getter
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "account_id")
    @Pattern(regexp = "[a-z\\d]{4,8}")
    private String accountId;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Novel> novelList;
}
