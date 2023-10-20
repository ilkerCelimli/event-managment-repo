package com.portifolyo.eventservice.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.portifolyo.requests.eventservice.ImageAndLinksReqeust;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDescriptionInfo implements Serializable{
    private String id;
    private LocalDateTime createdDate;
    private Boolean isDeleted;
    private String description;
    Set<ImageAndLinksReqeust> imageAndLinksList;
}
