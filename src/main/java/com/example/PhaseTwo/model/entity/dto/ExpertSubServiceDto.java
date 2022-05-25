package com.example.PhaseTwo.model.entity.dto;

public class ExpertSubServiceDto {
    private Long expertId;
    private Long subServiceId;

    public ExpertSubServiceDto(Long expertId, Long subServiceId) {
        this.expertId = expertId;
        this.subServiceId = subServiceId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public Long getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(Long subServiceId) {
        this.subServiceId = subServiceId;
    }
}
