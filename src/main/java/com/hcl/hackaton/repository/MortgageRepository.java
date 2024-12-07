package com.hcl.hackaton.repository;

import com.hcl.hackaton.entity.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
    public interface MortgageRepository extends JpaRepository<Mortgage, Long> {
    public Mortgage findById(long accountId);

}
