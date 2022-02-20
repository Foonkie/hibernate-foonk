package com.foonk.entitiy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Birthday {


    private LocalDate birthDate;

    public Birthday(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
}
