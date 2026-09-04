# Changelog

## Unreleased

## 5.1.0 - 2026-09-04

- Added vendor-neutral OpenTelemetry spans for logical SDK operations, HTTP attempts, response receipt, decoding, and safe failure categories.
- Added W3C trace-context propagation plus global or per-client OpenTelemetry configuration.
- Kept request bodies, credentials, resource identifiers, dynamic URLs, and exception details out of telemetry.

## 5.0.0 - 2026-09-03

- Breaking: moved wallet types into `com.inttegro.wallets`.
- Breaking: moved financial-account bank types into `com.inttegro.bankaccounts`.
- Kept payment-method bank snapshots in `com.inttegro.paymentmethods` as a separate domain concept.

## 4.1.0 - 2026-09-03

- Added the referenced product ID to returned catalog prices.

## 4.0.0 - 2026-09-03

- Breaking: replaced the catch-all `ApiEnums` constants with native enums in their domain packages.
- Breaking: consolidated Chime, broadcast, and schedule types in `com.inttegro.chimes`.
- Breaking: moved payment lifecycle types from `com.inttegro.orders` to `com.inttegro.payments` and payment-method constants to `com.inttegro.paymentmethods`.
- Breaking: replaced the catch-all `com.inttegro.common` package with the focused `com.inttegro.money` package.
- Typed enum-backed request and response fields instead of exposing raw strings.
- Added typed OTP request builders and nested verification and transmission objects.
- Separated request `AmountParams` and `PriceParams` from returned `Amount` and `Price` values.

## 3.0.1 - 2026-09-03

- Corrected the transport user agent and installation examples to match the released package.

## 3.0.0 - 2026-09-03

- Breaking: resource methods now return concrete domain objects and pages instead of response wrapper classes.
- Breaking: removed public response classes and renamed payment result status constants to `PaymentResultStatus`.

## 2.0.0 - 2026-09-02

- Breaking: renamed the primary client to `com.inttegro.Client`.
- Breaking: replaced public `*Models` container classes with domain packages such as `com.inttegro.customers`, `com.inttegro.orders`, and `com.inttegro.refunds`.

## 1.0.0 - 2026-09-01

- Breaking: renamed the Maven coordinates, Java packages, and client type to `com.inttegro:inttegro-sdk-java`, `com.inttegro.inttegro`, and `InttegroClient`.
- Aligned documentation, examples, and the transport user agent with the public Inttegro service name.
