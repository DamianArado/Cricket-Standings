package com.example.cricket.error;

public class ApplicationException extends RuntimeException 
{
    public ApplicationException() 
    {
        super();
    }

    public ApplicationException(String message) 
    {
        super(message);
    }

    public ApplicationException(String message, Throwable throwable) 
    {
        super(message, throwable);
    }
}
