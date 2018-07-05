package com.udacity.gradle.builditbigger.backend;

import com.example.javaprovider.GetJokeFromJava;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com"
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        if (new GetJokeFromJava().tellJoke() != null) {
            response.setData(new GetJokeFromJava().tellJoke());
        } else {
            response.setData(name);
        }

        return response;
    }

    @ApiMethod(name = "getJokes")
    public MyBean getJokes() {
        MyBean response = new MyBean();

        response.setData(new GetJokeFromJava().tellJoke());

        return response;
    }
}
