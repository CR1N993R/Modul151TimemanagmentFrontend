package ch.fenix.timemanagementfrontend.service.jfx;

import org.springframework.stereotype.Service;

@Service
public class DataTransferService {
    private static Object object;

    public Object getObject() {
        Object o = object;
        object = null;
        return o;
    }

    public void setObject(Object o) {
        object = o;
    }
}
