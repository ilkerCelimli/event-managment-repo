package com.portifolyo.eventservice.repository.projections;

import com.portifolyo.eventservice.entity.ImageAndLinks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDescriptionInfo {
    private String id;
    private Date createdDate;
    private Boolean isDeleted;
    private String descrtiption;
    Set<ImageAndLinks> imageAndLinksList;
}
