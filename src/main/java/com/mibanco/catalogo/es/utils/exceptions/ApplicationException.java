package com.mibanco.catalogo.es.utils.exceptions;

public class ApplicationException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    int status;

    public ApplicationException(int status, String msm) {
        super(msm);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
