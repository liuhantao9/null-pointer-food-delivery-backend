package edu.northeastern.cs5500.delivery;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.port;

public class App {

    public static final String SALT = "tW8YechgQt1i1xJKgsRmJtl5hr0vOv";

    static int getAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 5000; // run on port 5000 by default
    }

    public static void main(String[] arg) {
        // run on port 5000
        port(getAssignedPort());

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        // print all unhandled exceptions
        exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // load and start the server
        DaggerServerComponent.create().server().start();
    }
}
