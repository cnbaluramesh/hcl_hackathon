package com.hcl.hackaton.repository.impl;

import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.entity.Mortgage;
import com.hcl.hackaton.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class MortgageRepositoryImpl implements MortgageRepository {
    @Autowired
    private MortgageDTO mortgageDTO;

    @Autowired
    private Mortgage mortgage;

    @Override
    public Mortgage findById(long accountId) {
        mortgage.setMortgageId(mortgageDTO.getMortgageId());
        mortgage.setMortgageType(mortgageDTO.getMortgageType());
        mortgage.setMortgageBalance(mortgageDTO.getMortgageBalance());
        mortgage.setPropertyCost(mortgageDTO.getPropertyCost());
         return mortgage;
    }

}
