package com.example.assignment.domain.entity.novel;

import com.example.assignment.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Table(name = "novel")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "title")
    private String title;

    @JoinColumn(name = "main_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
