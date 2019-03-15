package com.challengue.wdvglab.controller;


import com.challengue.wdvglab.errors.ValidationErrorBuilder;
import com.challengue.wdvglab.models.Phrase;
import com.challengue.wdvglab.models.Transform;
import com.challengue.wdvglab.services.imp.TransoformServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/reverse")
public class TransformController {

    @Autowired
    TransoformServicesImp servicesImp;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> translatePhrase(@Valid @RequestBody Phrase phase, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        return new ResponseEntity<Transform>(servicesImp.process(phase), HttpStatus.OK);

    }
}
