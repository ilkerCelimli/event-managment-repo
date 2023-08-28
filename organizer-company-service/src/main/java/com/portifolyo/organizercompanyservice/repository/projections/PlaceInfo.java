package com.portifolyo.organizercompanyservice.repository.projections;

import org.portifolyo.requests.organizercompanyservice.InputEnum;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Projection for {@link com.portifolyo.organizercompanyservice.entity.Place}
 */
public interface PlaceInfo {
    String getId();

    LocalDateTime getCreatedDate();

    LocalDateTime getUpdatedDate();

    boolean isActive();

    String getName();

    Integer getMaxCapacity();

    Integer getMaxChair();

    AdressInfo getAdress();

    Set<InputInfo> getInputs();

    /**
     * Projection for {@link com.portifolyo.organizercompanyservice.entity.Adress}
     */
    interface AdressInfo {
        String getId();

        LocalDateTime getCreatedDate();

        LocalDateTime getUpdatedDate();

        boolean isActive();

        String getOpenAdress();

        CityInfo getCity();

        /**
         * Projection for {@link com.portifolyo.organizercompanyservice.entity.City}
         */
        interface CityInfo {
            String getId();

            String getCityName();

            String getCityCode();
        }
    }

    /**
     * Projection for {@link com.portifolyo.organizercompanyservice.entity.Input}
     */
    interface InputInfo {
        String getId();

        InputEnum getInputs();
    }
}