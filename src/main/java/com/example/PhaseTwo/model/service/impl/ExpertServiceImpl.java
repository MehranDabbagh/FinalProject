package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.SubService;
import com.example.PhaseTwo.model.entity.dto.ExpertDto;
import com.example.PhaseTwo.model.repository.ExpertRepository;
import com.example.PhaseTwo.model.repository.SubServiceRepository;
import com.example.PhaseTwo.model.service.ExpertService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpertServiceImpl implements ExpertService {
    private ExpertRepository expertRepository;
    private SubServiceRepository subServiceRepository;

    public ExpertServiceImpl(ExpertRepository expertRepository, SubServiceRepository subServiceRepository) {
        this.expertRepository = expertRepository;
        this.subServiceRepository = subServiceRepository;
    }

    @Override
    public ExpertDto save(Expert expert) {
        return convertingToDto(expertRepository.save(expert));
    }

    @Override
    public ExpertDto update(ExpertDto expert) {

        return convertingToDto(expertRepository.save(convertingToExpert(expert)));
    }

    @Override
    public ExpertDto findById(Long id) {
        Expert expert = expertRepository.findById(id).orElse(null);
        if (expert != null) {
            return convertingToDto(expert);
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public ExpertDto linkingExpertToSubService(ExpertDto expert, SubService subService) {
        Expert expert1 = expertRepository.findById(expert.getId()).orElse(null);
        SubService subService1 = subServiceRepository.findById(subService.getId()).orElse(null);
        if (expert1 != null && subService1 != null) {
            expert1.getSubServices().add(subService1);
            expertRepository.save(expert1);
            return convertingToDto(expert1);
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public List<ExpertDto> findingExpertsBySubService(SubService service) {
        SubService subService = subServiceRepository.findById(service.getId()).orElse(null);
        if (subService != null) {
            return expertRepository.findExpertBySubServicesContaining(subService).stream().map(expert -> convertingToDto(expert)).collect(Collectors.toList());
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public void changingPassword(Long Id, String password) {
        Expert expert = expertRepository.findById(Id).orElse(null);
        if (expert != null) {
            expert.setPassword(password);
            expertRepository.save(expert);
            return;
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public List<ExpertDto> findAll() {
        return expertRepository.findAll().stream().map(expert -> convertingToDto(expert)).collect(Collectors.toList());
    }

    @Override
    public void delete(ExpertDto expert) {
        expertRepository.deleteById(expert.getId());
    }

    private Example<Expert> createExample(String firstName, String lastName, String username) {
        Expert expert = new Expert();
        expert.setFirstname(firstName);
        expert.setLastname(lastName);
        expert.setUsername(username);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase("firstName", "lastName", "email")
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Expert> usersExample = Example.of(expert, matcher);
        return usersExample;
    }

    @Override
    public List<ExpertDto> findByOptional(String firstname, String lastname, String email, Long subServiceId) {
        Example<Expert> experts = createExample(firstname, lastname, email);
        List<Expert> experts1 = new ArrayList<>();
        if (subServiceId != null) {
            SubService subService = subServiceRepository.findById(subServiceId).orElse(null);
            if (subService != null) {
                expertRepository.findAll(experts).forEach(experts1::add);
                experts1.stream().filter(x -> x.getSubServices().contains(subService));
                return experts1.stream().map(expert -> convertingToDto(expert)).collect(Collectors.toList());
            }
        } else {
            expertRepository.findAll(experts).forEach(experts1::add);
            return experts1.stream().map(expert -> convertingToDto(expert)).collect(Collectors.toList());
        }

        throw new NullPointerException("no expert with this conditions!");

    }

    public ExpertDto convertingToDto(Expert expert) {
        ExpertDto expertDto = new ExpertDto(expert.getId(), expert.getFirstname(), expert.getLastname(), expert.getUsername(), expert.getVerified(), expert.getSingUpDate(), expert.getCredit(), expert.getImage(), expert.getPoint(), expert.getSubServices());
        return expertDto;
    }

    public Expert convertingToExpert(ExpertDto expertDto) {
        Expert expert = new Expert();
        expert.setId(expertDto.getId());
        expert.setFirstname(expertDto.getFirstName());
        expert.setLastname(expertDto.getLastName());
        expert.setUsername(expertDto.getUsername());
        expert.setSingUpDate(expertDto.getSingUpDate());
        expert.setCredit(expertDto.getCredit());
        expert.setVerified(expertDto.getVerified());
        expert.setRole(expertDto.getRole());
        return expert;
    }

}
