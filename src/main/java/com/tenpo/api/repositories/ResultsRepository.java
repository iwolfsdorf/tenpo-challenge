package com.tenpo.api.repositories;

import com.tenpo.api.entities.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ResultsRepository extends JpaRepository<Result, Long> {

    Page<Result> findAllByOrderByTimestampDesc(Pageable pageable);
}

