# Multi-Vendor E-Commerce Platform (Starter)

A production-ready starter scaffold for a multi-vendor e-commerce platform.

## Stack
- **Backend**: Spring Boot 3 (Web, Data JPA, Security, Validation), MySQL, JWT-ready, Stripe & Razorpay providers.
- **Frontend**: Angular skeleton with routes/components (instructions below).
- **Audit Logging**: JPA Auditing (createdAt/updatedAt).
- **Category Indexing**: SQL indexes in `schema.sql`.

## Quick Start (Backend)
1. Install Java 17, Maven, and MySQL.
2. Create a DB user (or use root) and update credentials in `src/main/resources/application.yml`.
3. Set payment keys (optional):
   - Stripe: `export STRIPE_API_KEY=sk_live_or_test`
   - Razorpay: `export RAZORPAY_KEY_ID=...` and `export RAZORPAY_KEY_SECRET=...`
4. Build & run:
   ```bash
   mvn spring-boot:run
   ```
5. Test endpoints:
   - `GET http://localhost:8080/api/products`
   - `POST http://localhost:8080/api/orders/pay?amount=500&currency=INR`

> Security is configured with HTTP Basic for now to keep the starter simple. Swap in JWT by plugging your `UserDetailsService` and an auth controller. Roles are `ADMIN`, `VENDOR`, `CUSTOMER`.

## Frontend (Angular) – Quick Setup
1. Install Node.js LTS and Angular CLI.
2. Create app:
   ```bash
   ng new shop --standalone --routing --style=scss
   cd shop
   npm i axios
   ```
3. Replace the generated `src/app` with the `/frontend/src/app` provided in this repo, then run:
   ```bash
   ng serve
   ```

## Next Steps
- Implement full JWT auth (register/login) returning a token, add `@PreAuthorize` guards.
- Build vendor dashboard screens to CRUD products and stock.
- Build admin dashboards for moderation and seller performance.
- Replace the `mockUser()` in `CartController` with the authenticated principal.
- Add shipping addresses and status transitions for orders.
