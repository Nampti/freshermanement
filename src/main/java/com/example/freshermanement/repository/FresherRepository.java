package com.example.freshermanement.repository;

import com.example.freshermanement.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Long> {
    Fresher findByFresherCode(String fresherCode);
}