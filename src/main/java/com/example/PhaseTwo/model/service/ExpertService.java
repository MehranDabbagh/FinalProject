package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.SubService;

import java.util.List;

public interface ExpertService {
    Expert save(Expert expert);
    Expert update(Expert expert);
    Expert findById(Long id);
    Expert linkingExpertToSubService(Expert expert, SubService subService);
    List<Expert> findingExpertsBySubService(SubService service);
    void changingPassword(Long Id,String password);

}
