package com.mycompany.employee.dto;

import java.util.List;

public class SocialInteractionDetailsResponse {
    private List<SocialInteractionDTO> socialInteractionDTOList;
    private int socialInteractionCount;

    public List<SocialInteractionDTO> getSocialInteractionDTOList() {
        return socialInteractionDTOList;
    }

    public void setSocialInteractionDTOList(List<SocialInteractionDTO> socialInteractionDTOList) {
        this.socialInteractionDTOList = socialInteractionDTOList;
    }

    public int getSocialInteractionCount() {
        return socialInteractionCount;
    }

    public void setSocialInteractionCount(int socialInteractionCount) {
        this.socialInteractionCount = socialInteractionCount;
    }
}
