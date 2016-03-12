package com.bugtag.networkapp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Async {

    private static final Executor executor = Executors.newCachedThreadPool();

    public static void run(Runnable task) {
        executor.execute(task);
    }
}
