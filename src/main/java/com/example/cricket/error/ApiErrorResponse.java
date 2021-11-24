package com.example.cricket.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ApiErrorResponse implements Serializable 
{

    private Integer status;

    private String path;

    private String message;

    private String exception;

    private Date timestamp;

    @JsonInclude(Include.NON_EMPTY)
    private List<ApiError> errors;

    private ApiErrorResponse(Integer status, String path, String message, String exception) 
    {
        this.status = status;
        this.path = path;
        this.message = message;
        this.exception = exception;
    }

    public static ApiErrorResponse valueOf(Integer status, String path, String message, String exception) 
    {
        return new ApiErrorResponse(status, path, message, exception);
    }

    public ApiErrorResponse() 
    {
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public String getPath() 
    {
        return path;
    }

    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getMessage() 
    {
        return message;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getException() 
    {
        return exception;
    }

    public void setException(String exception) 
    {
        this.exception = exception;
    }

    public Date getTimestamp() 
    {
        if (timestamp == null) 
        {
            timestamp = new Date();
        }
        return timestamp;
    }

    public void setTimestamp(Date timestamp) 
    {
        this.timestamp = timestamp;
    }

    public List<ApiError> getErrors() 
    {
        if (errors == null) 
        {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public void setErrors(List<ApiError> errors) 
    {
        this.errors = errors;
    }
}
