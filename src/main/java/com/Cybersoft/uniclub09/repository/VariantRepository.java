package com.Cybersoft.uniclub09.repository;

import com.Cybersoft.uniclub09.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
}
