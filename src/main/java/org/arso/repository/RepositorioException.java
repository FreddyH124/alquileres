package org.arso.repository;

public class RepositorioException extends Exception{

    public RepositorioException(String msg, Throwable causa) {
        super(msg, causa);
    }

    public RepositorioException(String msg) {
        super(msg);
    }

}
