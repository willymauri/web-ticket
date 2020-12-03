package net.puntonet.ticket.excepcion;

import javax.ejb.ApplicationException;

//@ApplicationException(rollback = true)
public class ExceptionService extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExceptionService() {
        super();
    }

    public ExceptionService(String message) {
        super(message);
    }

    public ExceptionService(Throwable cause) {
        super(cause);
    }

    public ExceptionService(String message, Throwable cause) {
        super(message, cause);
    }
}
