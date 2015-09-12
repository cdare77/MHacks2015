package com.walmart.openapi.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

import java.lang.annotation.Annotation;

public class GuiceInjector {

    private static final Injector INJECTOR = Guice.createInjector(new Dependencies());

    public static <T> T getInstance(Class<T> clazz) {
        return INJECTOR.getInstance(clazz);
    }

    public static <T> T getInstance(Class<T> clazz, Annotation annotation) {
        return INJECTOR.getInstance(Key.get(clazz, annotation));
    }

    public static <T> T getNamedInstance(Class<T> clazz, String name) {
        return getInstance(clazz, Names.named(name));
    }
}
