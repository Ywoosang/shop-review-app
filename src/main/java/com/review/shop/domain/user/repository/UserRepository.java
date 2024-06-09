package com.review.shop.domain.user.repository;

import com.review.shop.domain.user.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(0));
    }
}
