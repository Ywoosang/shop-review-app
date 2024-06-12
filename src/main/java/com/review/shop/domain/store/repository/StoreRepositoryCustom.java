package com.review.shop.domain.store.repository;

import com.review.shop.domain.store.enums.SortType;
import com.review.shop.domain.store.model.Store;
import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> findStoresWithPagination(Long lastId, int pageSize, SortType sortType);
}
