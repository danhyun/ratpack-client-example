import ratpack.guice.Guice;
import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;

public class App {
  public static void main(String[] args) throws Exception {
    RatpackServer.start(server -> server
        .serverConfig(c -> c.port(5051))
        .handlers(c -> c
          .get(ctx -> {
            User user = new User("gordon_freeman");
            ctx.render(Jackson.json(user));
          }))
    );

    RatpackServer.start(server -> server
        .registry(Guice.registry(b -> b.module(ClientModule.class)))
        .handlers(chain -> chain
          .get(ctx ->
            ctx.get(DownService.class)
              .load()
              .map(Jackson::json)
              .then(ctx::render)
          )
        )
    );
  }
}
