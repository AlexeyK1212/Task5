package ru.project.task5.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.project.task5.controllers.AccountController;
import ru.project.task5.dto.AccountDto;
import ru.project.task5.entities.TppProduct;
import ru.project.task5.entities.TppProductRegister;
import ru.project.task5.other.ErrorMessages;
import ru.project.task5.repositories.AccountPoolRep;
import ru.project.task5.repositories.TppProductRegisterRep;
import ru.project.task5.repositories.TppProductRep;
import ru.project.task5.repositories.TppRefProductRegisterTypeRep;


@Service
public class AccountServiceImpl  implements AccountService  {

    @Autowired
    private TppProductRegisterRep tppProductRegisterRep;


    @Autowired
    private TppRefProductRegisterTypeRep tppRefProductRegisterTypeRep;

    @Autowired
    private AccountPoolRep accountPoolRep;

    @Autowired
    private TppProductRep tppProductRep;

    @Autowired
    private ErrorMessages errorMessages;


    @Override
    public void create(AccountDto accountDto) {

            //Проверка таблицы ПР (таблица tpp_product_register) на дубли
            if (tppProductRegisterRep.getTppProductRegister(accountDto.instanceId, accountDto.registryTypeCode) != 0) {
                errorMessages.err = "Параметр registryTypeCode тип регистра " + accountDto.registryTypeCode + " уже существует для ЭП с ИД  " + accountDto.instanceId;
                errorMessages.st= HttpStatus.BAD_REQUEST;
                return;
            }

            //Взять значение из Request.Body.registryTypeCode и найти соответствующие ему записи в tpp_ref_product_register_type.value.
            String value = tppRefProductRegisterTypeRep.getByRegistryTypeCode(accountDto.registryTypeCode);
            if (value == null) {
                errorMessages.st= HttpStatus.NOT_FOUND  ;
                errorMessages.err = ": Код Продукта " + accountDto.registryTypeCode + " не найдено в Каталоге продуктов tpp_ref_product_register_type для данного типа Регистра";
                return;
            }

            //Найти значение номера счета по параметрам branchCode, currencyCode, mdmCode, priorityCode, registryTypeCode из Request.Body
            AccountPoolRep.AccountNumberStuct accountNumberStuct;
            accountNumberStuct = accountPoolRep.getAccount(
                    accountDto.branchCode,
                    accountDto.currencyCode,
                    accountDto.mdmCode,
                    accountDto.priorityCode,
                    accountDto.registryTypeCode);

            if(accountNumberStuct==null)
              {
                  errorMessages.st= HttpStatus.BAD_REQUEST;
                  errorMessages.err = "Ошибка поиска в account_pool branchCode="+accountDto.branchCode+" currencyCode="+accountDto.currencyCode+" mdmCode="+accountDto.mdmCode+" priorityCode="+accountDto.priorityCode+" instanceDto="+accountDto.registryTypeCode;
              return;
              }




            //Сформировать новый продуктовый регистр и записать его в БД.
            TppProductRegister tppProductRegister = new TppProductRegister();
            tppProductRegister.setProduct_id(accountDto.instanceId);
            //tppProductRegister.setProduct_id(accountDto.instanceId);
            tppProductRegister.setType(value);
            tppProductRegister.setAccount(accountNumberStuct.getId().longValue());
            tppProductRegister.setCurrency_code(accountDto.currencyCode);
            tppProductRegister.setState("1");
            tppProductRegister.setAccount_number(accountNumberStuct.getAccountnumber());

            tppProductRegister = tppProductRegisterRep.save(tppProductRegister);
            errorMessages.st=HttpStatus.OK;
        errorMessages.ok = "{\n" +
                "\"accountId\": \""+tppProductRegister.getId()+"\" \n" +
                "}\n";



    }





}
