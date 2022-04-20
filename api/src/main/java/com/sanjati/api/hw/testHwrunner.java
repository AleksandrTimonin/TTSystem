package com.sanjati.api.hw;

public class testHwrunner {
    public static void main(String[] args) throws Throwable {
        AbstractDtoFactory factory = new CoreDtoFactory();
        System.out.println(factory.errorReport());
    }
}
