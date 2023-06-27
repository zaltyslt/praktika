package lt.ks.vtmc.serviceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedServiceInfo extends RuntimeException {

    public DuplicatedServiceInfo(String message) {
        super(message);
    }
}
