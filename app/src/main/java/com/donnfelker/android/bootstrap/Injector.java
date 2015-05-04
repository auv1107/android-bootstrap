package com.donnfelker.android.bootstrap;

import com.donnfelker.android.bootstrap.util.Ln;

import dagger.ObjectGraph;

public final class Injector {

    private static ObjectGraph objectGraph = null;

    public static void init(final Object... modules) {

        objectGraph = ObjectGraph.create(modules);

        // Inject statics
        objectGraph.injectStatics();

    }

    public static void init(final Object target, Object... modules) {
        init(modules);
        inject(target);
    }

    public static void inject(final Object target) {
        objectGraph.inject(target);
    }

    public static void add(Object... objects) {
        Ln.d("add()");
        if (objectGraph == null) {
            objectGraph = ObjectGraph.create(objects);
        } else {
            objectGraph = objectGraph.plus(objects);
        }
        Ln.d("successfully added into objectgraph");

    }

    public static <T> T resolve(Class<T> type) {
        return objectGraph.get(type);
    }
}
