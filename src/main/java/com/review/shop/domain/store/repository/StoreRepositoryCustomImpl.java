package com.review.shop.domain.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.review.shop.domain.review.model.QReview;
import com.review.shop.domain.store.enums.SortType;
import com.review.shop.domain.store.model.QStore;
import com.review.shop.domain.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Store> findStoresWithPagination(Long lastId, int pageSize, SortType sortType) {
        return switch(sortType) {
            case LATEST -> findStoresByLatest(lastId, pageSize);
            case ORDEST -> findStoresByOldest(lastId, pageSize);
            case MOSTREVIEWED -> findStoresByMostReviewed(lastId,pageSize);
        };
    }

    private List<Store> findStoresByLatest(Long lastId, int pageSize) {
        QStore store = QStore.store;
        var query = queryFactory.selectFrom(store);

        if (lastId != null) {
            query.where(store.id.lt(lastId));
        }

        query.orderBy(store.id.desc());

        return query.limit(pageSize).fetch();
    }

    private List<Store> findStoresByOldest(Long lastId, int pageSize) {
        QStore store = QStore.store;
        var query = queryFactory.selectFrom(store);

        if (lastId != null) {
            query.where(store.id.gt(lastId));
        }
        query.orderBy(store.id.asc());

        return query.limit(pageSize).fetch();
    }

    private List<Store> findStoresByMostReviewed(Long lastId, int pageSize) {
        QStore store = QStore.store;
        QReview review = QReview.review;
        var query = queryFactory.selectFrom(store);

        if (lastId != null) {
            Long lastReviewCount = queryFactory.select(review.count())
                    .from(review)
                    .where(review.store.id.eq(lastId))
                    .fetchOne();
            query.where(store.id.lt(lastId)
                    .and(queryFactory.select(review.count())
                    .from(review)
                    .where(review.store.id.eq(store.id))
                    .goe(lastReviewCount)));
        }

        query.leftJoin(store.reviews, review)
                .groupBy(store.id)
                .orderBy(review.id.desc(), store.id.desc());

        return query.limit(pageSize).fetch();
    }
}
