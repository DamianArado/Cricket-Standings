package com.example.cricket.error;

public class InternalException extends RuntimeException 
{
    public InternalException() 
    {
        super();
    }

    public InternalException(String message) 
    {
        super(message);
    }

    public InternalException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}