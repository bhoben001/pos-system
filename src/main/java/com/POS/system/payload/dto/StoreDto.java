package com.POS.system.payload.dto;

import com.POS.system.Domain.StoreStatus;
import com.POS.system.Model.StoreContact;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonPropertyOrder({"id","brand","storeAdmin","createdAt","updatedAt","description","storeType","status","contact"})
public class StoreDto {

    private Long id;

    private String brand;

    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;

}
