import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class ClientModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(DownService.class).in(Scopes.SINGLETON);
  }
}
