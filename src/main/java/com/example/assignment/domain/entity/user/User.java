package com.example.assignment.domain.entity.user;

import com.example.assignment.domain.entity.novel.Novel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @JoinColumn(name = "nickname")
    private String name;

    @JoinColumn(name = "account_id")
    private String accountId;

    @JoinColumn(name = "password")
    private String password;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.REMOVE)
    private List<Novel> novelList;

}
