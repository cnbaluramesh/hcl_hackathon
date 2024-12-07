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

public class MortgageRepositoryImpl implements MortgageRepository {
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

    @Override
    public void flush() {

    }

    @Override
    public <S extends Mortgage> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Mortgage> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Mortgage> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Mortgage getOne(Long aLong) {
        return null;
    }

    @Override
    public Mortgage getById(Long aLong) {
        return null;
    }

    @Override
    public Mortgage getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Mortgage> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Mortgage> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Mortgage> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Mortgage> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Mortgage> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Mortgage> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Mortgage, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Mortgage> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Mortgage> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Mortgage> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Mortgage> findAll() {
        return null;
    }

    @Override
    public List<Mortgage> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Mortgage entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Mortgage> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Mortgage> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Mortgage> findAll(Pageable pageable) {
        return null;
    }
}
