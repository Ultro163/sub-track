package com.example.subtrack.dto.mappers;

import com.example.subtrack.dto.ResponseSubscriptionDto;
import com.example.subtrack.dto.SubscriptionDto;
import com.example.subtrack.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    SubscriptionDto toSubscriptionDto(Subscription subscription);

    ResponseSubscriptionDto toResponseSubscriptionDto(SubscriptionDto subscriptionDto);
}