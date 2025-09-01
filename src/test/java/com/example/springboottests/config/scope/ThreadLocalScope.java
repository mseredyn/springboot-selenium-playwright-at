package com.example.springboottests.config.scope;

public class ThreadLocalScope implements org.springframework.beans.factory.config.Scope {
    private static final ThreadLocal<java.util.Map<String, Object>> threadScope = ThreadLocal.withInitial(java.util.concurrent.ConcurrentHashMap::new);

    @Override
    public Object get(String name, org.springframework.beans.factory.ObjectFactory<?> objectFactory) {
        return threadScope.get().computeIfAbsent(name, k -> objectFactory.getObject());
    }

    @Override
    public Object remove(String name) {
        return threadScope.get().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return Thread.currentThread().getName();
    }
}
