const testimonials = [
  {
    quote:
      "GemMarket has become our primary export channel. Verification was seamless, and premium boosts deliver serious buyers quickly.",
    name: "Lakshika Fernando",
    role: "Director, Serendib Gems",
  },
  {
    quote:
      "The compliance dashboard and escrow concierge removed friction from high-value deals. Our close rate jumped 40% in three months.",
    name: "Azlan Thassim",
    role: "Founder, Aurora Jewel Traders",
  },
  {
    quote:
      "Buyers trust the documentation and instant messaging. We switched from traditional classifieds and now manage inventory digitally.",
    name: "Maya Perera",
    role: "Head of Sales, Ceylon Sapphire House",
  },
];

export function Testimonials() {
  return (
    <section className="border-t border-slate-200 bg-white py-24">
      <div className="mx-auto max-w-6xl px-6">
        <div className="flex flex-col gap-8 lg:flex-row lg:items-end lg:justify-between">
          <div className="max-w-xl space-y-4">
            <p className="text-sm font-semibold uppercase tracking-[0.3em] text-emerald-500">
              Testimonials
            </p>
            <h2 className="text-3xl font-semibold tracking-tight text-slate-900 sm:text-4xl">
              Trusted by Sri Lanka’s leading gem exporters and boutique jewelers.
            </h2>
            <p className="text-lg leading-8 text-slate-600">
              Teams rely on GemMarket to manage compliance, highlight premium stones, and meet buyers who value authenticity.
            </p>
          </div>
          <div className="flex items-center gap-4 text-xs uppercase tracking-[0.3em] text-slate-500">
            <span className="h-px w-8 bg-slate-200" />
            Verified voices
            <span className="h-px w-8 bg-slate-200" />
          </div>
        </div>
        <div className="mt-12 grid gap-6 lg:grid-cols-3">
          {testimonials.map((testimonial) => (
            <figure
              key={testimonial.name}
              className="relative overflow-hidden rounded-[28px] border border-slate-200 bg-gradient-to-br from-white via-slate-50 to-white p-8 shadow-[0_25px_80px_-60px_rgba(15,118,110,0.35)] transition hover:-translate-y-1"
            >
              <svg
                className="h-10 w-10 text-emerald-400"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeWidth="1.5"
              >
                <path d="M7.5 11.25v3.75a2.25 2.25 0 01-2.25 2.25m0 0h4.5m-4.5 0H3m7.5-6V6.375A2.625 2.625 0 013 6.375m18 4.875v3.75a2.25 2.25 0 01-2.25 2.25m0 0h4.5m-4.5 0H15" />
              </svg>
              <blockquote className="mt-6 text-base leading-7 text-slate-600">
                “{testimonial.quote}”
              </blockquote>
              <figcaption className="mt-8 text-sm">
                <p className="font-semibold text-slate-900">{testimonial.name}</p>
                <p className="text-slate-500">{testimonial.role}</p>
              </figcaption>
            </figure>
          ))}
        </div>
      </div>
    </section>
  );
}

