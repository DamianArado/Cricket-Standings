package com.example.cricket.base;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class BaseRequest implements Serializable 
{
    public static class Id implements Serializable 
    {
        @NotNull
        private Long id;

        public Long getId() 
        {
            return id;
        }

        public void setId(Long id) 
        {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) 
        {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;
            Id id1 = (Id) o;
            return Objects.equals(id, id1.id);
        }

        @Override
        public int hashCode() 
        {
            return Objects.hash(id);
        }
    }

    public static class GenId<T> implements Serializable 
    {
        @NotNull
        private T id;

        public T getId() 
        {
            return id;
        }

        public void setId(T id) 
        {
            this.id = id;
        }
    }

    public static class GenValue<T> implements Serializable 
    {
        @NotNull
        private T value;

        public T getValue() 
        {
            return value;
        }

        public void setValue(T value) 
        {
            this.value = value;
        }
    }
}
