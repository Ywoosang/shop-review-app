package com.review.shop.domain.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.review.shop.domain.review.model.QReview;
import com.review.shop.domain.store.enums.SortType;
import com.review.shop.domain.store.model.QRegion;
import com.review.shop.domain.store.model.QStore;
import com.review.shop.domain.store.model.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
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
        QRegion region = QRegion.region;
        var query = queryFactory.selectFrom(store)
                .leftJoin(store.region, region).fetchJoin();

        if (lastId != null) {
            query.where(store.id.lt(lastId));
        }

        query.orderBy(store.id.desc());

        return query.limit(pageSize).fetch();
    }

    private List<Store> findStoresByOldest(Long lastId, int pageSize) {
        QStore store = QStore.store;
        QRegion region = QRegion.region;
        var query = queryFactory.selectFrom(store)
                .leftJoin(store.region, region).fetchJoin();

        if (lastId != null) {
            query.where(store.id.gt(lastId));
        }
        query.orderBy(store.id.asc());

        return query.limit(pageSize).fetch();
    }

    private List<Store> findStoresByMostReviewed(Long lastId, int pageSize) {
        QStore store = QStore.store;
        QReview review = QReview.review;
        QRegion region = QRegion.region;

        Long lastReviewCount = null;
        if (lastId != null) {
            lastReviewCount = queryFactory.select(review.count())
                    .from(review)
                    .where(review.store.id.eq(lastId))
                    .fetchOne();
        }

        var query = queryFactory.selectFrom(store)
                .leftJoin(store.region, region).fetchJoin()
                .leftJoin(store.reviews, review)
                .groupBy(store.id);

        if (lastReviewCount != null) {
            query.having(
                    review.count().lt(lastReviewCount)
                            .or(review.count().eq(lastReviewCount).and(store.id.lt(lastId)))
            );
        }
        query.orderBy(store.id.desc());

        return query.limit(pageSize).fetch();
    }

}
