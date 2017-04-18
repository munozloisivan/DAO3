package Main;

import JSONController.JSONController;
import Models.Usuario;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    public static HttpServer startServer() {
        //Crea un recurso que escanea peticiones
        final ResourceConfig rc = new ResourceConfig().packages("DAO", "Exceptions", "JSONController", "Main", "Models");
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException, SQLException {

        //JSONController jsonController = new JSONController();
        //System.out.println(jsonController.getListUsuarios().get(0).getNombre()+ " " +jsonController.getListUsuarios().get(0).getId());

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.stop();

    }
}
