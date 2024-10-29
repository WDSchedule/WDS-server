package com.WDS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class WDSServerApplication
{
    public static void main( String[] args )
    {
        System.out.println(System.getenv("KEYSTORE_PATH"));
        SpringApplication.run(WDSServerApplication.class, args);
    }
}
