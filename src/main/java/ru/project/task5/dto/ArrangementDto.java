package ru.project.task5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
public class ArrangementDto {

    public String GeneralAgreementId; // ID доп.Ген.соглашения
    public String SupplementaryAgreementId; // ID доп.соглашения
    public String arrangementType; // Тип соглашения
    public Integer shedulerJobId; // integer-sequence	-  Идентификатор периодичности учета
    public String Number; // Номер ДС
    @JsonFormat(pattern="dd.MM.yyyy")
    public Timestamp openingDate; // date	+ Дата начала сделки
    public Timestamp closingDate; // date	- Дата окончания сделки
    public Timestamp CancelDate; // date	- Дата отзыва сделки
    public Integer validityDuration; // integer	- Срок действия сделки
    public String cancellationReason; // Причина расторжения
    public String	Status; // Состояние/статус
    public Timestamp interestCalculationDate; // date Начисление начинается с (дата)
    public float interestRate; // float Процент начисления на остаток
    public float coefficient; // float Коэффициент
    public String	coefficientAction; // Действие коэффициента
    public float minimumInterestRate; // Минимум по ставке float
    public String	minimumInterestRateCoefficient; // Коэффициент по минимальной ставке
    public String minimumInterestRateCoefficientAction; // Действие коэффициента по минимальной ставке
    public float maximalnterestRate; // Максимум по ставке //decimal
    public float maximalnterestRateCoefficient; // Коэффициент по максимальной ставке decimal
    public String	maximalnterestRateCoefficientAction; // Действие коэффициента по максимальной ставке


}
