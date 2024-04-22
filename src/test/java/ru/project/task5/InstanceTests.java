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

import ru.project.task5.controllers.InstanceController;
import ru.project.task5.other.ErrorMessages;
import ru.project.task5.services.InstanceService;
import ru.project.task5.dto.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InstanceController.class)
@Import(ErrorMessages.class)
@ContextConfiguration
public class InstanceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ErrorMessages errorMessages;

    @MockBean
    private InstanceService instanceService;

    @Test
    public void testCreateInstance() throws Exception {
        // Создаем тестовый объект InstanceDto
        InstanceDto testInstance = new InstanceDto();

                String jsonContent="{\n" +
                        "\"productType\" : \"test\",\n" +
                        "\"productCode\" : \"03.012.002\",\n" +
                        "\"registerType\" : \"03.012.002_47533_ComSoLd\",\n" +
                        "\"mdmCode\" : \"15\",\n" +
                        "\"contractNumber\" : \"141\",\n" +
                        "\"contractDate\" : \"01.01.2024\",\n" +
                        "\"priority\" : 12,\n" +
                        "\"contractId\" : 1,\n" +
                        "\"BranchCode\" : \"0022\",\n" +
                        "\"IsoCurrencyCode\" : \"800\",\n" +
                        "\"urgencyCode\" : \"12\",\n" +
                        "    \"instanceArrangement\" : [\n" +
                        "        {\"Number\" : \"1\", \"openingDate\" : \"02.01.2024\"},\n" +
                        "        {\"Number\" : \"2\", \"openingDate\" : \"03.01.2024\"}\n" +
                        "\n" +
                        "    ]\n" +
                        "\n" +
                        "}";



        // Настройка мока
        doNothing().when(instanceService).create(any(InstanceDto.class));

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());

        // Проверяем
        verify(instanceService).create(any(InstanceDto.class));
    }
}