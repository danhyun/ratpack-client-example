import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.exec.Promise;
import ratpack.http.client.HttpClient;

import javax.inject.Inject;
import java.net.URI;

public class DownService {
  private final HttpClient client;
  private final ObjectMapper mapper;
  private final URI uri;

  @Inject
  public DownService(HttpClient client, ObjectMapper mapper) {
    this.client = client;
    this.mapper = mapper;
    this.uri = URI.create("http://localhost:5051");
  }

  public Promise<User> load() {
    return client.get(uri).onError(Throwable::printStackTrace)
        .map(r -> r.getBody().getText())
        .map(t -> mapper.readValue(t, User.class));
  }
}
