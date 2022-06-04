package com.example.PhaseTwo.model.registration;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        String[] parts=s.split("@");
        if(parts.length<2){
            return false;
        }
        return true;
    }
}
