package com.review.shop.global.database;

import com.review.shop.domain.store.model.Region;
import com.review.shop.domain.store.repository.RegionRepository;
import com.review.shop.domain.user.model.FoodCategory;
import com.review.shop.domain.user.repository.FoodCategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Seeder {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initFoodCategories();
        initService.initRegions();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final FoodCategoryRepository foodCategoryRepository;
        private final RegionRepository regionRepository;

        public void initFoodCategories() {
            final Integer count = foodCategoryRepository.getCount();
            if (count > 0) return;
            final String[] foodCategoryNames = {
                    "한식", "일식", "중식",
                    "양식","치킨","분식",
                    "고기/구이", "도시락", "야식(족발,보쌈)",
                    "패스트푸드", "디저트", "아시안푸드"
            };

            Arrays.stream(foodCategoryNames).forEach(name -> {
                FoodCategory foodCategory = FoodCategory.builder()
                        .name(name)
                        .build();
                foodCategoryRepository.save(foodCategory);
            });
        }

        public void initRegions() {
            final Integer count = regionRepository.getCount();
            if (count > 0) return;
            final String[] regionNames = {
                    "고덕1동","고덕2동","상일동","강일동",
                    "명일1동","명일2동","길동",
                    "천호1동","천호2동","천호3동",
                    "성내1동","성내2동","성내3동",
                    "둔촌1동","둔촌2동","암사1동","암사2동","암사3동"
            };

            Arrays.stream(regionNames).forEach(name -> {
                Region region = Region.builder()
                        .name(name)
                        .build();
                regionRepository.create(region);
            });
        }
    }
}
