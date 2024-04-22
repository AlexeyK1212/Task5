package ru.project.task5.other;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessages {

    public String err="";
    public String ok="";

    public HttpStatus st=HttpStatus.OK;

}
