package com.hcl.hackaton.services;

<<<<<<< HEAD

public class MortgageService {


=======
import com.hcl.hackaton.dto.MortgageDTO;
import org.springframework.stereotype.Service;

@Service
public interface MortgageService {

    public abstract long showAccountBalance();

    long repay(long amt);

    public String authenticate(MortgageDTO mortgageDTO);
>>>>>>> fced052af314c44d36470a9e7bb00f052ef2b4bf
}