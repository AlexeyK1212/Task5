package ru.project.task5.other;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

//Копирует значения полей из одного объекта в другой
//при сравнении имен полей переводит их в нижний регистр и удаляет символы нижнего подчеркивания
public class FieldCopier {
    public static void copyFields(Object a, Object b) {
        Class<?> classA = a.getClass();
        Class<?> classB = b.getClass();

        Map<String, String> fieldNamesMap = new HashMap<>();

        // Заполняем HashMap названиями полей объекта b
        for (Field field : classB.getDeclaredFields()) {
            field.setAccessible(true);
            String normalizedFieldName = normalizeFieldName(field.getName());
            fieldNamesMap.put(normalizedFieldName, field.getName());
        }

        // Проходим по полям объекта a и копируем значения в b
        for (Field fieldA : classA.getDeclaredFields()) {
            fieldA.setAccessible(true);
            String normalizedFieldNameA = normalizeFieldName(fieldA.getName());

            if (fieldNamesMap.containsKey(normalizedFieldNameA)) {
                String fieldBName = fieldNamesMap.get(normalizedFieldNameA);
                try {
                    Field fieldB = classB.getDeclaredField(fieldBName);
                    fieldB.setAccessible(true);
                    fieldB.set(b, fieldA.get(a));
                } catch (Exception e){//NoSuchFieldException e) {
                    //Не удалось скопировать поле
                }
            }
        }
    }

    private static String normalizeFieldName(String fieldName) {
        return fieldName.replace("_", "").toLowerCase();
    }
}
