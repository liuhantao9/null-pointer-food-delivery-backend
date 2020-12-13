/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.northeastern.cs5500.delivery;

import dagger.Component;
import edu.northeastern.cs5500.delivery.model.ModelModule;
import edu.northeastern.cs5500.delivery.repository.RepositoryModule;
import edu.northeastern.cs5500.delivery.view.View;
import edu.northeastern.cs5500.delivery.view.ViewModule;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Component(
        modules = {
            ViewModule.class,
            ModelModule.class,
            RepositoryModule.class,
        })
@Singleton
interface ServerComponent {
    public Server server();
}

public class Server {

    @Inject Set<View> views;

    @Inject
    Server() {}

    void start() {
        for (View view : views) {
            System.out.println(view.getClass());
            view.register();
        }
    }
}
