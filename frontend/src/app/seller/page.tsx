import Link from "next/link";
import { SellerApplicationForm } from "@/components/SellerApplicationForm";

export default function SellerPage() {
  return (
    <div className="bg-slate-50 text-slate-900">
      <header className="relative overflow-hidden bg-gradient-to-b from-slate-950 via-slate-900 to-slate-950 pb-24 pt-28 text-white">
        <div className="absolute inset-0 -z-10">
          <div className="absolute -left-48 top-0 h-96 w-96 rounded-full bg-emerald-500/20 blur-3xl" />
          <div className="absolute right-[-12rem] bottom-[-4rem] h-[32rem] w-[32rem] rounded-full bg-cyan-500/10 blur-[140px]" />
          <div className="absolute inset-x-0 top-0 h-px bg-gradient-to-r from-transparent via-white/20 to-transparent" />
        </div>
        <div className="mx-auto flex max-w-5xl flex-col gap-8 px-6 text-center">
          <div>
            <p className="text-sm font-semibold uppercase tracking-[0.3em] text-emerald-200">
              Become a GemMarket seller
            </p>
            <h1 className="mt-4 text-4xl font-semibold leading-tight tracking-tight sm:text-5xl">
              Submit your credentials to unlock verified selling and premium promotions.
            </h1>
            <p className="mt-4 text-lg leading-8 text-slate-200">
              Provide NIC and GemReg documentation, choose the plan that fits your inventory, and our team will activate your seller console within a day.
            </p>
          </div>
          <div className="flex flex-wrap justify-center gap-3 text-sm text-slate-200">
            <span className="inline-flex items-center gap-2 rounded-full border border-white/20 px-4 py-1.5">
              <span className="h-2 w-2 rounded-full bg-emerald-300" />
              24-hour verification SLA
            </span>
            <span className="inline-flex items-center gap-2 rounded-full border border-white/20 px-4 py-1.5">
              Secure file storage
            </span>
            <span className="inline-flex items-center gap-2 rounded-full border border-white/20 px-4 py-1.5">
              Compliance handover
            </span>
          </div>
          <div className="flex flex-wrap items-center justify-center gap-4">
            <Link
              href="#application-form"
              className="inline-flex items-center justify-center rounded-full bg-white px-5 py-2.5 text-sm font-semibold text-slate-900 transition hover:-translate-y-0.5 hover:bg-slate-100"
            >
              Complete application
            </Link>
            <Link
              href="/"
              className="inline-flex items-center justify-center rounded-full border border-white/40 px-5 py-2.5 text-sm font-semibold text-white transition hover:border-white hover:bg-white/10"
            >
              Back to homepage
            </Link>
          </div>
        </div>
      </header>

      <main className="space-y-16 pb-24 pt-12">
        <section className="mx-auto max-w-5xl px-6 text-sm text-slate-600">
          <div className="grid gap-6 rounded-[28px] border border-slate-200 bg-white p-8 shadow-[0_30px_120px_-90px_rgba(15,118,110,0.4)] md:grid-cols-3">
            <div className="space-y-2">
              <p className="text-xs uppercase tracking-[0.3em] text-emerald-600">Documents</p>
              <p className="text-base font-semibold text-slate-900">Required uploads</p>
              <p>Government-issued NIC or Passport, and the latest GemReg certificate for gemstone trading.</p>
            </div>
            <div className="space-y-2">
              <p className="text-xs uppercase tracking-[0.3em] text-emerald-600">Review process</p>
              <p className="text-base font-semibold text-slate-900">Compliance review</p>
              <p>Our team performs KYC checks, AML screening, and certificate validation before activating your account.</p>
            </div>
            <div className="space-y-2">
              <p className="text-xs uppercase tracking-[0.3em] text-emerald-600">Go-live</p>
              <p className="text-base font-semibold text-slate-900">Marketplace access</p>
              <p>Once approved, you can publish listings, schedule premium boosts, and access the analytics dashboard.</p>
            </div>
          </div>
        </section>

        <section id="application-form" className="px-6">
          <SellerApplicationForm />
        </section>
      </main>
    </div>
  );
}


