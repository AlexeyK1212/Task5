package ru.project.task5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class InstanceDto {

    public Long instanceId;    // Идентификатор экземпляра продукта
    @NotNull(message = "Обязательный параметр productType не заполнен")
    public String productType; // Тип Экземпляра Продукта
    @NotNull(message = "Обязательный параметр productCode не заполнен")
    public String productCode; // Код продукта в каталоге продуктов
    @NotNull(message = "Обязательный параметр registerType не заполнен")
    public String registerType; // Тип регистра
    @NotNull(message = "Обязательный параметр mdmCode не заполнен")
    public String mdmCode; // Код Клиента (mdm)
    @NotNull(message = "Обязательный параметр contractNumber не заполнен")
    public String contractNumber; // Номер договора
    @NotNull(message = "Обязательный параметр contractDate не заполнен")
    @JsonFormat(pattern="dd.MM.yyyy")
    public Timestamp contractDate;//	date // Дата з
    // аключения договора обслуживания
    @NotNull(message = "Обязательный параметр priority не заполнен")
    public Long priority; // Приоритет
    public String PriorityCode;

    public Float interestRatePenalty; // Штрафная процентная ставка
    public Float minimalBalance; // Неснижаемый остаток
    public Float thresholdAmount; // Пороговая сумма
    public String accountingDetails; // Реквизиты выплаты
    public String rateType; // Выбор ставки в зависимости от суммы
    public Float taxPercentageRate; // Ставка налогообложения
    public Float technicalOverdraftLimitAmount; // Сумма лимита технического овердрафта
    @NotNull(message = "Обязательный параметр contractId не заполнен")
    public Integer contractId; // ID Договора
    @NotNull(message = "Обязательный параметр BranchCode не заполнен")
    public String BranchCode;// Код филиала
    @NotNull(message = "Обязательный параметр IsoCurrencyCode не заполнен")
    public String IsoCurrencyCode;// Код валюты
    @NotNull(message = "Обязательный параметр urgencyCode не заполнен")
    public String urgencyCode;// Код срочности договора
    public Integer ReferenceCode;// Код точки продаж
    public AdditionalPropertiesVip additionalPropertiesVip;
    public ArrangementDto[] instanceArrangement;


}
