package edu.northeastern.cs5500.delivery;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class JsonTransformer_Factory implements Factory<JsonTransformer> {
  @Override
  public JsonTransformer get() {
    return newInstance();
  }

  public static JsonTransformer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static JsonTransformer newInstance() {
    return new JsonTransformer();
  }

  private static final class InstanceHolder {
    private static final JsonTransformer_Factory INSTANCE = new JsonTransformer_Factory();
  }
}
