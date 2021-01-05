package com.iftm.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError implements Serializable {

    private static final long serialVersionUID = -384004696746954553L;

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(){

    }

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path){
        super(timestamp,status,error,message,path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String message){
        erros.add( new FieldMessage(fieldName,message) );
    }
}
