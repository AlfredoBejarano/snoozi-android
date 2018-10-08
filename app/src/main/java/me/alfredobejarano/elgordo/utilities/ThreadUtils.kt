package me.alfredobejarano.elgordo.utilities

import java.util.concurrent.Executors

/**
 * Utils class that provides functions for common thread operations.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 08/10/2018 - 05:17 PM
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Executes work in a background thread.
 */
fun runOnWorkerThread(f: () -> Unit) = IO_EXECUTOR.execute(f)