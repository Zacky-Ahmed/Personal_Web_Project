export function PremiumPromo() {
  return (
    <section
      id="premium"
      className="relative overflow-hidden bg-gradient-to-br from-emerald-500 via-teal-500 to-cyan-500 py-24 text-white"
    >
      <div className="absolute inset-0 -z-10">
        <div className="absolute -left-24 top-16 h-64 w-64 rounded-full bg-white/20 blur-3xl" />
        <div className="absolute right-0 bottom-0 h-80 w-80 rounded-full bg-white/10 blur-[120px]" />
      </div>
      <div className="mx-auto grid max-w-6xl gap-14 px-6 lg:grid-cols-[1.1fr_0.9fr] lg:items-center">
        <div className="space-y-6">
          <p className="text-sm font-semibold uppercase tracking-[0.3em] text-white/80">
            Premium badge
          </p>
          <h2 className="text-3xl font-semibold tracking-tight sm:text-4xl">
            Showcase hero listings with homepage placement and smart retargeting.
          </h2>
          <p className="text-lg leading-8 text-white/85">
            Premium boosts elevate your gem to the top of search results, feature it in curated collections,
            and trigger tailored buyer notifications. It’s the fastest path to qualified conversations.
          </p>
          <ul className="grid gap-4 text-sm text-white/90 sm:grid-cols-2">
            {[
              "Homepage takeover with badge & animation",
              "AI buyer matching and instant lead alerts",
              "Performance dashboard with conversion insights",
              "Optional escrow concierge for closing deals",
            ].map((feature) => (
              <li key={feature} className="flex items-start gap-3">
                <svg
                  className="mt-1 h-5 w-5"
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
          <div className="flex flex-wrap items-center gap-4 pt-2">
            <a
              href="#plans"
              className="inline-flex items-center justify-center rounded-full bg-white px-6 py-3 text-sm font-semibold text-emerald-600 transition hover:-translate-y-0.5 hover:bg-white/90"
            >
              See premium pricing
            </a>
            <a
              href="#contact"
              className="inline-flex items-center justify-center rounded-full border border-white/50 px-6 py-3 text-sm font-semibold text-white transition hover:border-white"
            >
              Talk to an expert
            </a>
          </div>
        </div>
        <div className="relative overflow-hidden rounded-[32px] border border-white/20 bg-white/15 p-8 backdrop-blur-xl shadow-[0_45px_120px_-60px_rgba(15,118,110,0.6)]">
          <div className="absolute -top-20 right-0 h-60 w-60 rounded-full bg-white/10 blur-3xl" />
          <div className="relative space-y-6 text-sm text-white/85">
            <div className="flex items-center justify-between rounded-2xl border border-white/20 bg-white/10 px-4 py-3">
              <span className="text-xs uppercase tracking-[0.3em] text-white/70">Boost summary</span>
              <span className="rounded-full bg-white/20 px-3 py-1 text-xs font-semibold text-white">Active</span>
            </div>
            <div className="grid gap-4 rounded-2xl border border-white/20 bg-white/10 p-5">
              <div className="space-y-1">
                <p className="text-xs uppercase tracking-[0.3em] text-white/60">Listing</p>
                <h3 className="text-lg font-semibold text-white">
                  Blue Sapphire Cabochon · 5.1ct
                </h3>
                <p>Featured until 18 Nov · Colombo, Sri Lanka</p>
              </div>
              <div className="grid grid-cols-2 gap-4 text-sm">
                <div>
                  <p className="text-xs uppercase tracking-[0.2em] text-white/60">Buyer views</p>
                  <p className="mt-1 text-xl font-semibold text-white">2,340</p>
                  <p className="text-xs text-white/70">+86% vs. standard</p>
                </div>
                <div>
                  <p className="text-xs uppercase tracking-[0.2em] text-white/60">Lead quality</p>
                  <p className="mt-1 text-xl font-semibold text-white">92%</p>
                  <p className="text-xs text-white/70">Qualified score</p>
                </div>
              </div>
            </div>
            <div className="grid grid-cols-3 gap-4 rounded-2xl border border-white/20 bg-white/10 p-5">
              {[
                { label: "Views", value: "8.1k" },
                { label: "Saves", value: "430" },
                { label: "Leads", value: "18" },
              ].map((metric) => (
                <div key={metric.label}>
                  <p className="text-xs uppercase tracking-[0.2em] text-white/60">{metric.label}</p>
                  <p className="mt-1 text-lg font-semibold text-white">{metric.value}</p>
                </div>
              ))}
            </div>
            <button className="w-full rounded-full bg-white px-4 py-2 text-sm font-semibold text-emerald-600 transition hover:bg-white/90">
              Activate premium boost
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}

