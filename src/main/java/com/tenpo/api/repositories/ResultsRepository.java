package com.tenpo.api.repositories;

import com.tenpo.api.entities.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsRepository extends JpaRepository<Result, Long> {

    Page<Result> findAllByOrderByDateTimeDesc(Pageable pageable);
}

