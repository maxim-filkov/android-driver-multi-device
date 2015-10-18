package com.multidevice.test.framework;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * This cache class holds a map where test scoped beans are cached, ensuring that only one instance is created. It also
 * has a reset method for clearing the cache before each test run.
 */
public class TestScope implements Scope {

  private final Map<String, Object> cache = new HashMap<>();

  /**
   * Resets cache, it is used for clearing the cache before each test run.
   */
  public void reset() {
    cache.clear();
  }

  @Override
  public Object get(final String name, final ObjectFactory<?> objectFactory) {
    if (!cache.containsKey(name)) {
      cache.put(name, objectFactory.getObject());
    }
    return cache.get(name);
  }

  @Override
  public Object remove(final String name) {
    return cache.remove(name);
  }

  @Override
  public void registerDestructionCallback(final String name, final Runnable callback) {
  }

  @Override
  public Object resolveContextualObject(final String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }
}
