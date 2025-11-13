const partners = [
  "Serendib Gems",
  "Aurora Jewel Traders",
  "Luxe Gem Labs",
  "Ceylon Sapphire House",
  "Sri Gem Exporters",
  "Global Jewel Exchange",
];

export function TrustedBy() {
  return (
    <section className="bg-white py-16">
      <div className="mx-auto flex max-w-6xl flex-col items-center gap-10 px-6 text-center">
        <p className="text-sm font-semibold uppercase tracking-[0.3em] text-slate-500">
          Trusted by leading gemstone brands
        </p>
        <div className="grid w-full grid-cols-2 gap-6 text-sm font-medium text-slate-500 sm:grid-cols-3 lg:grid-cols-6">
          {partners.map((partner) => (
            <div
              key={partner}
              className="rounded-2xl border border-slate-200 bg-white px-4 py-3 shadow-[0_15px_50px_-40px_rgba(15,118,110,0.4)]"
            >
              {partner}
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

