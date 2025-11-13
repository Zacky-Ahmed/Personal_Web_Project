export function FeaturedListings() {
  return (
    <section className="relative overflow-hidden bg-slate-950 py-24 text-white">
      <div className="absolute inset-0 -z-10">
        <div className="absolute left-16 top-6 h-64 w-64 rounded-full bg-emerald-500/20 blur-[120px]" />
        <div className="absolute right-20 bottom-0 h-80 w-80 rounded-full bg-cyan-500/20 blur-[140px]" />
      </div>
      <div className="mx-auto max-w-6xl px-6">
        <div className="flex flex-col gap-5 sm:flex-row sm:items-end sm:justify-between">
          <div className="max-w-xl space-y-3">
            <p className="text-sm font-semibold uppercase tracking-[0.3em] text-emerald-300">
              Premium gems
            </p>
            <h2 className="text-3xl font-semibold sm:text-4xl">
              Spotlight listings powered by premium boosts.
            </h2>
            <p className="text-slate-300">
              Premium sellers unlock AI-matched leads, homepage placement, and deeper analytics on buyer intent.
            </p>
          </div>
          <a
            href="#"
            className="inline-flex items-center gap-2 rounded-full border border-white/20 px-5 py-2.5 text-sm font-semibold text-white transition hover:border-white hover:bg-white/10"
          >
            View marketplace
            <svg
              className="h-4 w-4"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="1.6"
            >
              <path strokeLinecap="round" strokeLinejoin="round" d="M5 12h14m0 0l-6-6m6 6l-6 6" />
            </svg>
          </a>
        </div>
        <div className="mt-12 grid gap-6 lg:grid-cols-3">
          {[
            {
              title: "Untreated Cornflower Sapphire · 3.2ct",
              location: "Colombo, Sri Lanka",
              price: "US$ 9,850",
              badge: "Premium boost · 6 days left",
              gradient: "from-emerald-400/25 via-teal-400/15 to-transparent",
            },
            {
              title: "Pigeon Blood Ruby Suite · 2.1ct",
              location: "Ratnapura, Sri Lanka",
              price: "US$ 6,500",
              badge: "Homepage spotlight",
              gradient: "from-rose-400/25 via-orange-400/20 to-transparent",
            },
            {
              title: "Alexandrite Colour Change · 1.6ct",
              location: "Galle, Sri Lanka",
              price: "US$ 4,300",
              badge: "GemLab certified",
              gradient: "from-purple-400/25 via-indigo-400/20 to-transparent",
            },
          ].map((listing) => (
            <article
              key={listing.title}
              className="group relative overflow-hidden rounded-[32px] border border-white/10 bg-white/5 p-6 backdrop-blur-sm transition hover:-translate-y-2 hover:border-white/20"
            >
              <div
                className={`absolute right-0 top-0 h-full w-full blur-3xl transition duration-500 group-hover:opacity-80 group-hover:blur-[110px] opacity-0 bg-gradient-to-br ${listing.gradient}`}
              />
              <div className="relative space-y-6">
                <span className="inline-flex items-center gap-2 rounded-full bg-white/10 px-3 py-1 text-xs font-semibold uppercase tracking-[0.2em] text-white/80">
                  <span className="h-2 w-2 rounded-full bg-emerald-300" />
                  {listing.badge}
                </span>
                <div className="space-y-3">
                  <h3 className="text-lg font-semibold leading-tight">{listing.title}</h3>
                  <p className="text-sm text-slate-200">{listing.location}</p>
                </div>
                <div className="rounded-2xl border border-white/15 bg-white/10 px-4 py-3 text-sm text-slate-200">
                  <div className="flex items-center justify-between">
                    <span>Asking price</span>
                    <span className="text-base font-semibold text-white">{listing.price}</span>
                  </div>
                  <div className="mt-3 flex items-center justify-between text-xs uppercase tracking-[0.2em] text-white/70">
                    <span>Documents · GIA, GemReg</span>
                    <span>Views · 2.3k</span>
                  </div>
                </div>
                <button className="w-full rounded-full bg-white/90 px-4 py-2 text-sm font-semibold text-slate-900 transition hover:bg-white">
                  Contact seller
                </button>
              </div>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}

