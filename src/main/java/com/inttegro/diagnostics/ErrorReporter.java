package com.inttegro.diagnostics;

/** Receives one report after a logical SDK operation finally fails. */
@FunctionalInterface
public interface ErrorReporter {
    /** Implementations should enqueue quickly; reporter failures are isolated by the SDK. */
    void report(ErrorReport report);
}
