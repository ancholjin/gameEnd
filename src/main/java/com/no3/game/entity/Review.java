package com.no3.game.entity;

import lombok.*;
import java.util.Optional;
import javax.persistence.*;
import java.util.Set;

@Table(name="review")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"item","member"})
public class Review extends BaseEntity {

    @Id
    @Column(name="review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;    // 리뷰 내용
    private int grade;      // 평점



    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "review" ,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<ItemImg> imgs;

    public void addPhoto(ItemImg itemImg){
        imgs.add(itemImg);
    }




    // 수정
    public void changeGrade(int grade){
        this.grade = grade;
    }
    public void changeText(String text){
        this.text = text;
    }
}
