export function SellerWorkflow() {
  return (
    <section id="workflow" className="border-t border-b border-slate-200/60 bg-white py-24">
      <div className="mx-auto max-w-6xl px-6">
        <div className="max-w-3xl space-y-5">
          <p className="text-sm font-semibold uppercase tracking-[0.3em] text-emerald-500">
            Seller journey
          </p>
          <h2 className="text-3xl font-semibold tracking-tight text-slate-900 sm:text-4xl">
            Compliance-first onboarding with concierge support at every step.
          </h2>
          <p className="text-lg leading-8 text-slate-600">
            From document checks to premium promotion, GemMarket automates the manual work so you can scale your inventory and reach vetted buyers faster.
          </p>
        </div>
        <div className="mt-14 grid gap-8 lg:grid-cols-[0.9fr_1.1fr]">
          <div className="relative overflow-hidden rounded-[32px] border border-slate-200/80 bg-gradient-to-br from-slate-950 via-slate-900 to-slate-950 p-8 text-white shadow-[0_40px_120px_-50px_rgba(8,145,178,0.55)]">
            <div className="absolute -left-20 top-0 h-56 w-56 rounded-full bg-emerald-500/20 blur-3xl" />
            <div className="absolute right-0 bottom-0 h-72 w-72 rounded-full bg-cyan-500/20 blur-[120px]" />
            <div className="relative space-y-6">
              <div className="flex items-center justify-between">
                <span className="text-xs font-semibold uppercase tracking-[0.35em] text-emerald-200">
                  Seller command center
                </span>
                <span className="inline-flex items-center gap-2 rounded-full border border-white/15 bg-white/10 px-3 py-1 text-xs font-medium text-white/80">
                  <span className="h-2 w-2 rounded-full bg-emerald-300" />
                  Live
                </span>
              </div>
              <div className="rounded-2xl border border-white/10 bg-white/5 p-5 text-sm text-slate-200">
                <div className="flex items-center justify-between text-xs uppercase tracking-[0.2em] text-white/60">
                  Verification status
                  <span>Updated · 5 mins ago</span>
                </div>
                <div className="mt-4 space-y-3">
                  <div className="flex items-center justify-between rounded-xl border border-white/10 bg-white/5 px-4 py-3">
                    <span>NIC review</span>
                    <span className="rounded-full bg-emerald-400/20 px-3 py-1 text-xs font-medium text-emerald-100">
                      Approved
                    </span>
                  </div>
                  <div className="flex items-center justify-between rounded-xl border border-white/10 bg-white/5 px-4 py-3">
                    <span>GemReg certificate</span>
                    <span className="rounded-full bg-emerald-400/20 px-3 py-1 text-xs font-medium text-emerald-100">
                      Approved
                    </span>
                  </div>
                  <div className="flex items-center justify-between rounded-xl border border-white/10 bg-white/5 px-4 py-3">
                    <span>AML checks</span>
                    <span className="rounded-full bg-cyan-400/20 px-3 py-1 text-xs font-medium text-cyan-100">
                      Pending</span>
                  </div>
                </div>
              </div>
              <div className="grid grid-cols-2 gap-4 text-sm text-slate-200">
                <div className="rounded-2xl border border-white/10 bg-white/5 px-4 py-4">
                  <p className="text-xs uppercase tracking-[0.2em] text-white/60">Renewal reminder</p>
                  <p className="mt-2 text-lg font-semibold text-white">12 days</p>
                </div>
                <div className="rounded-2xl border border-white/10 bg-white/5 px-4 py-4">
                  <p className="text-xs uppercase tracking-[0.2em] text-white/60">Premium boosts left</p>
                  <p className="mt-2 text-lg font-semibold text-white">3</p>
                </div>
              </div>
            </div>
          </div>
          <div className="grid gap-6">
            {[
              {
                title: "1. Submit documents",
                description: "Upload NIC, GemReg certificate, and business profile. Automatic OCR checks flag issues instantly.",
              },
              {
                title: "2. Get verified & subscribe",
                description: "Choose the right monthly plan, set up payments, and activate premium boosts once approval lands.",
              },
              {
                title: "3. Launch listings",
                description: "Import inventory, schedule releases, and enrich listings with high-resolution media and certificates.",
              },
              {
                title: "4. Capture and manage leads",
                description: "Real-time notifications, buyer vetting, and CRM integrations keep conversations authentic and fast.",
              },
            ].map((step, index) => (
              <div
                key={step.title}
                className="group relative overflow-hidden rounded-3xl border border-slate-200 bg-white p-6 transition hover:-translate-y-1 hover:border-emerald-200"
              >
                <div className="absolute right-[-5rem] top-[-4rem] h-36 w-36 rounded-full bg-emerald-400/10 blur-3xl transition duration-500 group-hover:opacity-80 group-hover:blur-[70px]" />
                <div className="relative flex gap-4">
                  <span className="mt-1 inline-flex h-10 w-10 items-center justify-center rounded-2xl bg-emerald-500/10 text-base font-semibold text-emerald-600">
                    {index + 1}
                  </span>
                  <div className="space-y-2">
                    <h3 className="text-lg font-semibold text-slate-900">{step.title}</h3>
                    <p className="text-sm text-slate-600">{step.description}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </section>
  );
}

