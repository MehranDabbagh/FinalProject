package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.entity.Users;
import com.example.PhaseTwo.model.repository.ExpertRepository;
import com.example.PhaseTwo.model.repository.SubServiceRepository;
import com.example.PhaseTwo.model.service.ExpertService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {
    private ExpertRepository expertRepository;
    private SubServiceRepository subServiceRepository;

    public ExpertServiceImpl(ExpertRepository expertRepository, SubServiceRepository subServiceRepository) {
        this.expertRepository = expertRepository;
        this.subServiceRepository = subServiceRepository;
    }

    @Override
    public Expert save(Expert expert) {
        return expertRepository.save(expert);
    }

    @Override
    public Expert update(Expert expert) {

        return expertRepository.save(expert);
    }

    @Override
    public Expert findById(Long id) {
        return expertRepository.findById(id).orElse(null);
    }

    @Override
    public Expert linkingExpertToSubService(Expert expert, SubService subService) {
        Expert expert1 = expertRepository.findById(expert.getId()).orElse(null);
        SubService subService1 = subServiceRepository.findById(subService.getId()).orElse(null);
        if (expert1 != null && subService1 != null) {
            expert1.getSubServices().add(subService1);
            expertRepository.save(expert1);
            return expert1;
        }
        return null;
    }

    @Override
    public List<Expert> findingExpertsBySubService(SubService service) {
        SubService subService = subServiceRepository.findById(service.getId()).orElse(null);
        if (subService != null) {
            return expertRepository.findExpertBySubServicesContaining(subService);
        }
        return null;
    }

    @Override
    public void changingPassword(Long Id, String password) {
        Expert expert = expertRepository.findById(Id).orElse(null);
        if (expert != null && expert.getUsers().passwordChecking(password)) {
            expert.getUsers().setPassword(password);
            expertRepository.save(expert);
        }
    }

    @Override
    public List<Expert> findAll() {
        return expertRepository.findAll();
    }

    @Override
    public void delete(Expert expert) {
        expertRepository.deleteById(expert.getId());
    }
    private Example<Expert> createExample(String firstName, String lastName, String email) {
        Expert expert = new Expert();
        Users users=new Users();
        users.setFirstname(firstName);
        users.setLastname(lastName);
        users.setEmail(email);
        expert.setUsers(users);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase("firstName", "lastName", "email")
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Expert> usersExample = Example.of(expert, matcher);
        return usersExample;
    }
    @Override
    public List<Expert> findByOptional(String firstname, String lastname, String email, SubService subService) {
       Example<Expert> experts=createExample(firstname,lastname,email);
       List<Expert> experts1=new ArrayList<>();
       expertRepository.findAll(experts).forEach(experts1::add);
      experts1.stream().filter(x->x.getSubServices().contains(subService));
       return experts1;


    }

}
