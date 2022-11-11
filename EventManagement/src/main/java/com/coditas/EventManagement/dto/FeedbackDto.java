package com.coditas.EventManagement.dto;

import lombok.Data;

@Data
public class FeedbackDto {

    private String feedbackComment;
    private float feedBackRating;
    private Long eventId;
    private Long customerId;
}
