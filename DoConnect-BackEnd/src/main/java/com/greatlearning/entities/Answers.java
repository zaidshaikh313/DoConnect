package com.greatlearning.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answers")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int a_id;
    @Lob
    @Column(name = "answer",length = 512)
    private String answer;
    private String approved;
    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "qid")
    private Questions questions;

}
