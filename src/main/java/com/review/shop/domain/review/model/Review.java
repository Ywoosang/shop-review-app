package com.review.shop.domain.review.model;

import com.review.shop.global.model.BaseEntity;
import com.review.shop.domain.store.model.Store;
import com.review.shop.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder
    public Review(String content, Long score) {
        this.content = content;
        this.score = score;
    }

    public void addUser(User user) {
        this.user = user;
    }

    public void addStore(Store store) {
        this.store = store;
    }
}
