package com.fleetmanagement.demo.repository;

import com.fleetmanagement.demo.models.TaxiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<TaxiModel, Integer> {

    Page<TaxiModel> findAll(Pageable pageable);
}
