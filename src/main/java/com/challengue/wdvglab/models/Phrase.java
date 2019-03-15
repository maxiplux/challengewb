package com.challengue.wdvglab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phrase {

    @Size(min = 1, max = 1023, message = "Phrase must be between 1 and  1024 characters of length")
    @NotEmpty(message = "Not empty values")
    @NotNull(message = "Phrase may not be null")
    private String phrase;
}


