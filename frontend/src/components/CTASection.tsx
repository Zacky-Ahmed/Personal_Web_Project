export function CTASection() {
  return (
    <section
      id="contact"
      className="relative overflow-hidden bg-white py-24"
    >
      <div className="absolute inset-x-0 top-0 h-px bg-gradient-to-r from-transparent via-slate-200 to-transparent" />
      <div className="absolute inset-x-0 bottom-0 h-px bg-gradient-to-r from-transparent via-slate-200 to-transparent" />
      <div className="mx-auto max-w-5xl px-6 text-center">
        <div className="relative overflow-hidden rounded-[40px] border border-slate-200 bg-gradient-to-br from-white via-slate-50 to-white px-10 py-16 shadow-[0_45px_120px_-70px_rgba(16,185,129,0.3)]">
          <div className="absolute -left-32 top-0 h-72 w-72 rounded-full bg-emerald-500/10 blur-[120px]" />
          <div className="absolute right-[-6rem] bottom-[-5rem] h-80 w-80 rounded-full bg-cyan-500/10 blur-[140px]" />
          <div className="relative space-y-6">
            <p className="text-sm font-semibold uppercase tracking-[0.3em] text-emerald-500">
              Concierge onboarding
            </p>
            <h2 className="text-3xl font-semibold tracking-tight text-slate-900 sm:text-4xl">
              Launch your GemMarket storefront in less than 24 hours.
            </h2>
            <p className="text-lg leading-8 text-slate-600">
              Our specialists review documents, configure payment workflows, and help script your first premium listing so you can attract buyers immediately.
            </p>
            <div className="flex flex-wrap items-center justify-center gap-4 pt-4">
              <a
                href="#plans"
                className="inline-flex items-center justify-center rounded-full bg-gradient-to-r from-emerald-500 via-teal-500 to-cyan-500 px-6 py-3 text-sm font-semibold text-white shadow-lg shadow-emerald-500/30 transition hover:-translate-y-0.5 hover:from-emerald-400 hover:via-teal-500 hover:to-cyan-400"
              >
                Book onboarding call
              </a>
              <a
                href="mailto:hello@gemmarket.app"
                className="inline-flex items-center justify-center rounded-full border border-slate-200 px-6 py-3 text-sm font-semibold text-slate-700 transition hover:border-slate-300 hover:text-slate-900"
              >
                hello@gemmarket.app
              </a>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

