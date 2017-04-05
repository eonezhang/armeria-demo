package com.eone.rpc.armeria;

import com.example.thrift.HelloService;

import com.linecorp.armeria.common.http.HttpSessionProtocols;
import com.linecorp.armeria.common.thrift.ThriftSerializationFormats;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.server.thrift.THttpService;

public class Application {

    public static void main(String[] args) {
        HelloService.AsyncIface handler = new HelloServiceImpl();
        ServerBuilder builder = new ServerBuilder();
        builder.port(8080, HttpSessionProtocols.HTTP);

        builder.serviceAt("/hello", THttpService.of(handler, ThriftSerializationFormats.BINARY).decorate(LoggingService::new)).build();
        builder.serviceUnder("/docs", new DocService());

        Server server = builder.build();
        server.start().join();
    }
}
