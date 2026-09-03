# Changelog

## Unreleased

## 4.0.0 - 2026-09-03

- Breaking: replaced the catch-all `ApiEnums` constants with native enums in their domain packages.
- Typed enum-backed request and response fields instead of exposing raw strings.
- Added typed OTP request builders and nested verification and transmission objects.
- Corrected price creation amounts to use `common.Money`.

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
