package ru.project.task5.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import ru.project.task5.other.FieldCopier;
import ru.project.task5.dto.ArrangementDto;
import ru.project.task5.dto.InstanceDto;
import ru.project.task5.entities.Agreement;
import ru.project.task5.entities.TppProduct;
import ru.project.task5.entities.TppProductRegister;
import ru.project.task5.other.ErrorMessages;
import ru.project.task5.repositories.*;
//import ru.project.task5.repository.ClientRepository;

import java.util.List;

@Service
public class InstanceServiceImpl implements InstanceService {




 //   @Autowired
//   private ClientRepository clientRepository;

    // @Autowired
    //  private Clients clientdb;

 //   @Autowired
//   private ClientMapper clientMapper;


    @Autowired
    private TppProductRep tppProductRep;
    @Autowired
    private AgreementRep agreementRep;
    @Autowired
    private TppRefProductClassRep tppRefProductClassRep;
    @Autowired
    private TppRefProductRegisterTypeRep tppRefProductRegisterTypeRep;
    @Autowired
    private AccountPoolRep accountPoolRep;
    @Autowired
    private TppProductRegisterRep tppProductRegisterRep;
    //@Autowired
    //private ProductCode productCode;
    @Autowired
    private ErrorMessages errorMessages;


    @Override
    public void create(InstanceDto instanceDto) {//} throws NoSuchFieldException, IllegalAccessException {
        Long tppProductId=0L;
        Long registerId=0L;
        String AgreementId="";

        //Если ИД ЭП в поле Request.Body.instanceId не задано (NULL/Пусто),
        // то выполняется процесс создания нового экземпляра
        if(instanceDto.instanceId == null) {
            //Проверка таблицы ЭП (tpp_product) на дубли. Для этого необходимо отобрать строки по условию
            // tpp_product.number == Request.Body.ContractNumber
           if(tppProductRep.findFirstByNumber(instanceDto.contractNumber)!=null)
           {
               errorMessages.st= HttpStatus.BAD_REQUEST;
               errorMessages.err = "Параметр ContractNumber № договора "+instanceDto.contractNumber+" уже существует для ЭП с ИД "+instanceDto.instanceId;
               return;
           }

           //Проверка таблицы ДС (agreement) на дубли
           //Если есть записи по условию agreement.number == Request.Body.Arrangement[N].Number
           for(ArrangementDto arrangementDto : instanceDto.instanceArrangement)

               if (agreementRep.findByNumber(arrangementDto.Number) .size()>0) {
                   errorMessages.st = HttpStatus.BAD_REQUEST;
                   errorMessages.err = "Параметр № Дополнительного соглашения (сделки) Number " + arrangementDto.Number + " уже существует для ЭП с ИД " + instanceDto.instanceId;
                   return;
               }


           //По КодуПродукта найти связные записи в Каталоге Типа регистра
           List<String> tppRefProductRegisterTypeList = tppRefProductRegisterTypeRep.getTppRefProductRegisterType(instanceDto.productCode);
           if(tppRefProductRegisterTypeList.isEmpty())
             {
                 errorMessages.st= HttpStatus.NOT_FOUND;
                 errorMessages.err = "КодПродукта "+instanceDto.productCode+" не найден в Каталоге продуктов tpp_ref_product_class";
              return;
             }

           //Добавить строку в таблицу tpp_product
           //Запомнить новый ИД ЭП tpp_product.id
           TppProduct tppProduct = new TppProduct();
           tppProduct.setType(instanceDto.registerType);
           tppProduct.setNumber(instanceDto.contractNumber);
           tppProduct.setPriority(instanceDto.priority);
           tppProduct= tppProductRep.save(tppProduct);
           tppProductId=tppProduct.getId();

            //Добавить в таблицу ПР (tpp_product_registry) строки, заполненные
            AccountPoolRep.AccountNumberStuct accountNumberStuct;
            accountNumberStuct=accountPoolRep.getAccount(
                    instanceDto.BranchCode,
                    instanceDto.IsoCurrencyCode,
                    instanceDto.mdmCode,
                    instanceDto.PriorityCode,
                    instanceDto.registerType);
            if(accountNumberStuct==null)
            {
                errorMessages.st= HttpStatus.BAD_REQUEST;
                errorMessages.err = "Ошибка поиска в account_pool BranchCode="+instanceDto.BranchCode+" IsoCurrencyCode="+instanceDto.IsoCurrencyCode+" mdmCode="+instanceDto.mdmCode+" PriorityCode="+instanceDto.PriorityCode+" instanceDto="+instanceDto.registerType;
                return;
            }


            TppProductRegister tppProductRegister = new TppProductRegister();
            tppProductRegister.setProduct_id(tppProductId);
            tppProductRegister.setAccount(accountNumberStuct.getId().longValue());
            tppProductRegister.setAccount_number(accountNumberStuct.getAccountnumber());
            tppProductRegister.setType(instanceDto.registerType);
            tppProductRegisterRep.save(tppProductRegister);
            registerId=tppProductRegister.getId();
            errorMessages.ok="\"instanceId\": \""+tppProductId+"\", \n" +
                    "\"registerId\": "+registerId+",\n" +
                    "\"supplementaryAgreementId\": [ \n" +AgreementId+"]\n" +
                    "}\n";

            return;
        }

        //Шаг 2.1
        //Проверка таблицы ЭП (tpp_product) на существование ЭП
        if(tppProductRep.findById(instanceDto.instanceId)==null)
        {
            errorMessages.st= HttpStatus.NOT_FOUND;
            errorMessages.err = "Экземпляр продукта с параметром instanceId "+instanceDto.instanceId+" не найден";
            return;
        }

        //Шаг 2.2
        //Проверка таблицы ДС (agreement) на дубли
        for(ArrangementDto arrangementDto : instanceDto.instanceArrangement)
            if (agreementRep.findByNumber(arrangementDto.Number) .size()>0)  {
                errorMessages.st= HttpStatus.BAD_REQUEST;
                errorMessages.err = "Параметр № Дополнительного соглашения (сделки) Number " + arrangementDto.Number + " уже существует для ЭП с ИД " + instanceDto.instanceId;
                return;
            }


        //Шаг 8.
        //Добавить строку в таблицу ДС (agreement)
        Agreement agreement = new Agreement();
        Integer i=1;
        for(ArrangementDto arrangementDto : instanceDto.instanceArrangement)
            {
                FieldCopier fieldCopier = new FieldCopier();
                fieldCopier.copyFields(arrangementDto,agreement);
                agreement.setProduct_id(tppProductId);
                agreementRep.save(agreement);
                if(i==1)
                  AgreementId=AgreementId+"\"AgreementId"+i+"\" : "+agreement.getId();
                else
                  AgreementId=AgreementId+", \"AgreementId"+i+"\" : "+agreement.getId();
            i++;
            }

        errorMessages.ok="\"instanceId\": \""+tppProductId+"\", \n" +
                "\"registerId\": "+registerId+",\n" +
                "\"supplementaryAgreementId\": [ \n" +AgreementId+"]\n" +
                "}\n";


        //tppProductId


    }








}