package ru.project.task5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AccountDto {

    @NotNull(message = "Обязательный параметр instanceId не заполнен")
    public Long instanceId;
    public String registryTypeCode;
    public String accountType;
    public String currencyCode;
    public String branchCode;
    public String priorityCode;
    public String mdmCode;
    public String clientCode;
    public String trainRegion;
    public String counter;
    public String salesCode;


}
