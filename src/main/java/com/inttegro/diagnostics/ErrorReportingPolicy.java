package com.inttegro.diagnostics;

/** Controls which final SDK failures are reported. */
public enum ErrorReportingPolicy {
    /** SDK, transport, decoding, and server failures. */
    UNEXPECTED,
    /** Every final SDK failure except cancellation. */
    ALL
}
