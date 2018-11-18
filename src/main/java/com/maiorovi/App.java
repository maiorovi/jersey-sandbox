package com.maiorovi;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class App {

    public static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Starting webapp");

        HttpServer httpServer = startHttpServer();

        httpServer.start();


        System.in.read();
        Runtime.getRuntime().addShutdownHook(new Thread(httpServer::stop));
    }

    private static HttpServer startHttpServer() throws IOException {
        final ResourceConfig resourceConfig = new PackagesResourceConfig("com.maiorovi.resources");

        return GrizzlyServerFactory.createHttpServer(getBaseURI(), resourceConfig);
    }
}
