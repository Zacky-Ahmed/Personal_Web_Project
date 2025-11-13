import Link from "next/link";

export function HeroSection() {
  return (
    <section className="relative isolate overflow-hidden bg-gradient-to-br from-slate-950 via-slate-900 to-slate-950 pb-24 pt-32 text-white">
      <div className="absolute inset-0 -z-10">
        <div className="absolute -left-52 top-0 h-96 w-96 rounded-full bg-emerald-500/20 blur-3xl" />
        <div className="absolute right-[-14rem] top-28 h-[32rem] w-[32rem] rounded-full bg-cyan-500/10 blur-[140px]" />
        <div className="absolute inset-x-0 top-0 h-px bg-gradient-to-r from-transparent via-white/15 to-transparent" />
      </div>
      <div className="mx-auto grid max-w-6xl gap-12 px-6 lg:grid-cols-[1.05fr_0.95fr] lg:items-center">
        <div className="space-y-10">
          <span className="inline-flex items-center gap-2 rounded-full border border-white/15 bg-white/10 px-4 py-1.5 text-sm font-medium text-white/80 backdrop-blur">
            <span className="h-2 w-2 rounded-full bg-emerald-400" />
            Trusted by certified gem sellers worldwide
          </span>
          <div className="space-y-6">
            <h1 className="text-4xl font-semibold leading-tight tracking-tight sm:text-5xl">
              A premium marketplace for authenticated gemstones and serious buyers.
            </h1>
            <p className="max-w-xl text-lg leading-8 text-slate-200">
              Showcase your inventory to collectors, exporters, and jewel houses with full compliance.
              GemMarket verifies every seller and amplifies premium gems with AI-powered matchmaking.
            </p>
          </div>
          <div className="flex flex-wrap items-center gap-4">
            <Link
              href="/seller"
              className="inline-flex items-center justify-center gap-2 rounded-full bg-white px-6 py-3 text-sm font-semibold text-slate-900 shadow-xl shadow-emerald-500/30 transition hover:-translate-y-0.5 hover:bg-slate-100"
            >
              Start selling
              <svg
                className="h-4 w-4"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeWidth="1.6"
              >
                <path strokeLinecap="round" strokeLinejoin="round" d="M5 12h14m0 0l-6-6m6 6l-6 6" />
              </svg>
            </Link>
            <Link
              href="#marketplace"
              className="inline-flex items-center justify-center rounded-full border border-white/20 px-6 py-3 text-sm font-semibold text-white transition hover:border-white hover:bg-white/10"
            >
              Explore listings
            </Link>
          </div>
          <div className="flex flex-wrap gap-6 text-sm text-slate-300">
            <div>
              <p className="text-2xl font-semibold text-white">24h</p>
              <p>Average verification time</p>
            </div>
            <div>
              <p className="text-2xl font-semibold text-white">97%</p>
              <p>Buyer satisfaction score</p>
            </div>
            <div>
              <p className="text-2xl font-semibold text-white">12+</p>
              <p>Premium boosts per seller</p>
            </div>
          </div>
        </div>
        <div className="relative">
          <div className="absolute -top-20 right-8 h-48 w-48 rounded-full bg-emerald-400/20 blur-3xl" />
          <div className="absolute -bottom-12 left-0 h-72 w-72 rounded-full bg-cyan-400/15 blur-[120px]" />
          <div className="relative rounded-[32px] border border-white/15 bg-white/5 p-8 backdrop-blur-lg shadow-[0_32px_80px_-32px_rgba(6,182,212,0.5)]">
            <div className="space-y-6">
              <div className="flex items-center justify-between">
                <div className="text-xs font-semibold uppercase tracking-[0.35em] text-emerald-200">
                  Live spotlight
                </div>
                <span className="inline-flex items-center gap-1 rounded-full bg-emerald-500/15 px-3 py-1 text-xs text-emerald-200">
                  <span className="h-2 w-2 animate-pulse rounded-full bg-emerald-300" />
                  Premium
                </span>
              </div>
              <div className="space-y-4">
                <div className="rounded-2xl border border-white/10 bg-white/5 px-5 py-4">
                  <p className="text-sm uppercase tracking-[0.2em] text-emerald-200">Certified</p>
                  <h3 className="mt-2 text-lg font-semibold">Ceylon Blue Sapphire · 4.8ct</h3>
                  <div className="mt-3 flex items-center justify-between text-sm text-slate-200">
                    <span>Colombo, Sri Lanka</span>
                    <span>US$ 11,900</span>
                  </div>
                </div>
                <div className="rounded-2xl border border-white/10 bg-white/5 px-5 py-4">
                  <p className="text-sm uppercase tracking-[0.2em] text-cyan-200">New Arrival</p>
                  <h3 className="mt-2 text-lg font-semibold">Imperial Ruby Cluster · 2.3ct</h3>
                  <div className="mt-3 flex items-center justify-between text-sm text-slate-200">
                    <span>Ratnapura, Sri Lanka</span>
                    <span>US$ 7,650</span>
                  </div>
                </div>
              </div>
              <div className="rounded-2xl border border-white/10 bg-white/5 px-5 py-4">
                <div className="flex items-center justify-between text-xs uppercase tracking-[0.2em] text-white/70">
                  Engagement
                  <span>Last 7 days</span>
                </div>
                <div className="mt-4 grid grid-cols-2 gap-4 text-sm text-slate-200">
                  <div>
                    <p className="text-xl font-semibold text-white">9.4k</p>
                    <p>Listing impressions</p>
                  </div>
                  <div>
                    <p className="text-xl font-semibold text-white">182</p>
                    <p>Qualified leads</p>
                  </div>
                </div>
                <div className="mt-6 h-24 rounded-xl bg-gradient-to-br from-emerald-500/20 via-teal-500/20 to-cyan-400/20" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

