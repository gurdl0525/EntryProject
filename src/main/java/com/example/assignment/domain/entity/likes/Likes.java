package com.example.assignment.domain.entity.likes;

import com.example.assignment.domain.entity.novel.Novel;
import com.example.assignment.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
