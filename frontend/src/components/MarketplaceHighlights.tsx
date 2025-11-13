export function MarketplaceHighlights() {
  return (
    <section id="marketplace" className="relative overflow-hidden bg-white py-24">
      <div className="absolute inset-x-0 top-0 h-px bg-gradient-to-r from-transparent via-slate-200 to-transparent" />
      <div className="absolute inset-x-0 bottom-0 h-px bg-gradient-to-r from-transparent via-slate-200 to-transparent" />
      <div className="mx-auto flex max-w-6xl flex-col gap-16 px-6 lg:flex-row lg:items-start">
        <div className="flex-1 space-y-6">
          <p className="text-sm font-semibold uppercase tracking-[0.3em] text-emerald-500">
            Marketplace
          </p>
          <h2 className="text-3xl font-semibold tracking-tight text-slate-900 sm:text-4xl">
            Built for transparency, driven by credibility.
          </h2>
          <p className="text-lg leading-8 text-slate-600">
            Each listing pairs crystal-clear media, GemReg certificates, and seller reputation scores.
            Buyers view authenticity badges, chat in real time, and request inspections seamlessly.
          </p>
          <div className="grid gap-4 sm:grid-cols-3">
            {[
              { label: "Verified sellers", value: "420+" },
              { label: "Certified gemstones", value: "3.6k" },
              { label: "Global buyers monthly", value: "18k" },
            ].map((stat) => (
              <div
                key={stat.label}
                className="rounded-3xl border border-slate-200 bg-slate-50 px-6 py-8 shadow-[0_20px_60px_-40px_rgba(12,74,110,0.4)]"
              >
                <p className="text-xs uppercase tracking-[0.3em] text-slate-500">{stat.label}</p>
                <p className="mt-3 text-3xl font-semibold text-slate-900">{stat.value}</p>
              </div>
            ))}
          </div>
        </div>
        <div className="flex-1 space-y-4">
          {[
            {
              name: "Curated gem collections",
              description:
                "Filter by origin, lab certification, cut quality, and price. Save search alerts for collector-grade stones.",
              accent: "from-blue-400/50 via-indigo-400/40 to-slate-200/70",
            },
            {
              name: "Immersive media vault",
              description:
                "Upload 4K video, 360° spins, and macro photography. Buyers preview gems in realistic light simulations.",
              accent: "from-emerald-400/40 via-teal-300/30 to-slate-200/70",
            },
            {
              name: "Lead concierge",
              description:
                "Smart lead routing, response time analytics, and CRM integrations keep negotiations moving fast.",
              accent: "from-purple-400/40 via-fuchsia-300/30 to-slate-200/70",
            },
            {
              name: "Compliance dashboard",
              description:
                "Manage renewals, view verification statuses, and access audit logs directly from your seller console.",
              accent: "from-amber-400/40 via-orange-300/30 to-slate-200/70",
            },
          ].map((feature) => (
            <div
              key={feature.name}
              className="group relative overflow-hidden rounded-3xl border border-slate-200/80 bg-white px-6 py-6 transition-all duration-300 hover:-translate-y-1 hover:border-transparent hover:shadow-[0_40px_120px_-60px_rgba(16,185,129,0.45)]"
            >
              <div
                className={`pointer-events-none absolute inset-0 opacity-0 blur-3xl transition duration-500 group-hover:opacity-100 group-hover:blur-[80px] bg-gradient-to-br ${feature.accent}`}
              />
              <div className="relative space-y-2">
                <h3 className="text-lg font-semibold text-slate-900">{feature.name}</h3>
                <p className="text-sm text-slate-600">{feature.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

