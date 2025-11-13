export function SubscriptionPlans() {
  return (
    <section id="plans" className="bg-slate-950 py-24 text-white">
      <div className="mx-auto max-w-6xl px-6">
        <div className="flex flex-col gap-12 lg:flex-row lg:items-center lg:justify-between">
          <div className="max-w-xl space-y-5">
            <p className="text-sm font-semibold uppercase tracking-[0.3em] text-emerald-300">
              Pricing
            </p>
            <h2 className="text-3xl font-semibold tracking-tight sm:text-4xl">
              Choose the partnership that matches your pipeline.
            </h2>
            <p className="text-lg leading-8 text-slate-200">
              Subscriptions are flexible and cancellable. Upgrade plans seamlessly as inventory grows, and add premium boosts on demand for special drops.
            </p>
            <div className="grid gap-4 text-sm text-slate-300">
              <div className="flex items-start gap-3">
                <span className="mt-1 h-2 w-2 rounded-full bg-emerald-400" />
                3% platform fee only when a lead converts to a closed deal.
              </div>
              <div className="flex items-start gap-3">
                <span className="mt-1 h-2 w-2 rounded-full bg-emerald-400" />
                Pause subscriptions anytime without losing verification history.
              </div>
            </div>
          </div>
          <div className="flex flex-col items-start gap-4 text-sm text-slate-300">
            <div className="rounded-3xl border border-white/15 bg-white/5 px-5 py-4 backdrop-blur">
              <p className="text-xs uppercase tracking-[0.3em] text-white/70">
                Concierge add-ons
              </p>
              <p className="mt-2 text-sm">
                Escrow support, logistics coordination, and gemstone photography available on request.
              </p>
            </div>
            <div className="flex items-center gap-3 text-xs uppercase tracking-[0.3em] text-white/60">
              <span className="h-px flex-1 bg-white/20" />
              Monthly plans
              <span className="h-px flex-1 bg-white/20" />
            </div>
          </div>
        </div>
        <div className="mt-12 grid gap-6 lg:grid-cols-3">
          {[
            {
              name: "Starter Seller",
              price: "US$ 19",
              descriptor: "Perfect for boutique jewelers",
              features: [
                "Up to 5 active listings",
                "Document compliance tracker",
                "Email lead notifications",
                "Performance snapshot dashboard",
              ],
            },
            {
              name: "Growth Seller",
              price: "US$ 45",
              descriptor: "Most selected",
              featured: true,
              features: [
                "Up to 25 active listings",
                "2 premium boosts included",
                "WhatsApp & SMS lead alerts",
                "Advanced analytics & exports",
              ],
            },
            {
              name: "Elite Trader",
              price: "US$ 85",
              descriptor: "For large-scale traders",
              features: [
                "Unlimited listings & boosts",
                "Dedicated account manager",
                "Buyer vetting & escrow concierge",
                "Custom API integrations",
              ],
            },
          ].map((plan) => (
            <div
              key={plan.name}
              className={`relative flex h-full flex-col gap-6 rounded-[32px] border border-white/10 bg-white/5 p-6 backdrop-blur transition hover:-translate-y-2 hover:border-emerald-200/60 ${
                plan.featured ? "ring-2 ring-emerald-400/60 shadow-[0_50px_120px_-70px_rgba(16,185,129,0.9)]" : ""
              }`}
            >
              {plan.descriptor && (
                <span className="inline-flex w-fit items-center gap-2 rounded-full border border-white/20 bg-white/10 px-3 py-1 text-xs font-semibold uppercase tracking-[0.3em] text-white/80">
                  {plan.descriptor}
                </span>
              )}
              <div>
                <h3 className="text-2xl font-semibold text-white">{plan.name}</h3>
                <p className="mt-3 text-3xl font-semibold text-white">
                  {plan.price}
                  <span className="text-base font-medium text-white/70"> / month</span>
                </p>
              </div>
              <ul className="space-y-3 text-sm text-slate-200">
                {plan.features.map((feature) => (
                  <li key={feature} className="flex items-start gap-3">
                    <svg
                      className="mt-1 h-4 w-4 text-emerald-300"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      strokeWidth="1.6"
                    >
                      <path strokeLinecap="round" strokeLinejoin="round" d="M4.5 12.75l6 5.25 9-13.5" />
                    </svg>
                    <span>{feature}</span>
                  </li>
                ))}
              </ul>
              <button className="mt-auto w-full rounded-full bg-white px-4 py-2 text-sm font-semibold text-slate-900 transition hover:bg-white/90">
                Choose plan
              </button>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

