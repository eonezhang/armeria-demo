package com.eone.rpc.armeria;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import com.example.thrift.HelloService.AsyncIface;

public class HelloServiceImpl implements AsyncIface {
    @Override
    public void hello(String name, AsyncMethodCallback<String> resultHandler) throws TException {
        resultHandler.onComplete("Hello " + name);
    }
}
