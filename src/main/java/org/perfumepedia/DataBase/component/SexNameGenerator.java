package org.perfumepedia.DataBase.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SexNameGenerator {

    @Autowired
    private MessageGenerator messageGenerator;

    public String getSexName(int sex) {
        if (sex == 1) return messageGenerator.getMessage("female");
        if (sex == 2) return messageGenerator.getMessage("male");
        if (sex == 3) return messageGenerator.getMessage("unisex");
        return "NaN";
    }
}
