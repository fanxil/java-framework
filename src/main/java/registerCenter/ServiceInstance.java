package registerCenter;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceInstance implements Serializable {
    private String serviceName;
    private String host;
    private int port;

    public ServiceInstance(String serviceName, String host, int port) {
        this.serviceName = serviceName;
        this.host = host;
        this.port = port;
    }

    // getters and setters...
}