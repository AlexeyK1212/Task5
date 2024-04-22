package ru.project.task5.services;



import ru.project.task5.dto.InstanceDto;

public interface InstanceService {




    void create(InstanceDto instanceDto) throws NoSuchFieldException, IllegalAccessException;



}