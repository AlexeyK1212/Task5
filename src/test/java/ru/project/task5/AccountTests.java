package ru.project.task5;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import ru.project.task5.controllers.AccountController;
import ru.project.task5.other.ErrorMessages;
import ru.project.task5.services.AccountService;
import ru.project.task5.dto.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@Import(ErrorMessages.class)
@ContextConfiguration
public class AccountTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ErrorMessages errorMessages;

    @MockBean
    private AccountService accountService;

    @Test
    public void testCreateAccount() throws Exception {
        // Создаем тестовый объект AccountDto
        AccountDto testAccount = new AccountDto();

        String jsonContent="{\n" +
                "\"instanceId\" : 12,\n" +
                "\"registryTypeCode\" : \"03.012.002_47533_ComSoLd\",\n" +
                "\"branchCode\" : \"0022\",\n" +
                "\"currencyCode\" : \"800\",\n" +
                "\"mdmCode\" : \"15\",\n" +
                "\"priorityCode\" : \"00\"\n" +
                " }";



        // Настройка мока
        doNothing().when(accountService).create(any(AccountDto.class));

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());

        // Проверяем
        verify(accountService).create(any(AccountDto.class));
    }
}