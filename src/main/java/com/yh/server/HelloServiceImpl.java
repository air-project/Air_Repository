package com.yh.server;

import javax.jws.WebService;

@WebService(endpointInterface = "server.IHelloService", serviceName = "HelloService")
public class HelloServiceImpl implements IHelloService {

    public String sayHello(String username) {
        return "hello, " + username;
    }

}

