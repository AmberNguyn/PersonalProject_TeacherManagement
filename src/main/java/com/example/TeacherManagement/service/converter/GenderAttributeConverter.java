package com.example.TeacherManagement.service.converter;

import com.example.TeacherManagement.entity.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)

public class GenderAttributeConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender!=null? gender.getValue() : 3;
    }

    @Override
    public Gender convertToEntityAttribute(Integer integer) {
        if (integer != null)
        return Arrays.stream(Gender.values())
                .filter(each -> each.getValue() == integer)
                .findFirst()
                .orElse(Gender.OTHER);
        return Gender.OTHER;
    }
}
