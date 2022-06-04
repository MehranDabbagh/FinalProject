package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.*;
import com.example.PhaseTwo.model.entity.dto.ExpertDto;

import java.util.List;

public interface ExpertService {
    ExpertDto save(Expert expert);
    ExpertDto update(ExpertDto expert);
    ExpertDto findById(Long id);
    ExpertDto linkingExpertToSubService(ExpertDto expert, SubService subService);
    List<ExpertDto> findingExpertsBySubService(SubService service);
    void changingPassword(Long Id,String password);
    List<ExpertDto> findAll();
    void delete(ExpertDto expert);
    List<ExpertDto> findByOptional(String firstname,String lastname,String email,Long subServiceId);
    List<Bid> findBids(Long expertId);
    List<Orders> MatchingOrders(Long expertId);
}
