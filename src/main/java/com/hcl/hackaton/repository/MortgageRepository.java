package com.hcl.hackaton.repository;

import com.hcl.hackaton.entity.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
    public abstract class MortgageRepository implements JpaRepository<Mortgage, Long> {


}
