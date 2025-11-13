import Link from "next/link";

const navigation = [
  { label: "Marketplace", href: "#marketplace" },
  { label: "How It Works", href: "#workflow" },
  { label: "Plans", href: "#plans" },
  { label: "Premium", href: "#premium" },
  { label: "Contact", href: "#contact" },
];

export function Navbar() {
  return (
    <header className="sticky top-0 z-50">
      <div className="mx-auto flex w-full max-w-6xl items-center justify-between px-6 py-4">
        <div className="flex items-center gap-2 text-lg font-semibold text-slate-900">
          <span className="grid h-10 w-10 place-content-center rounded-2xl bg-gradient-to-br from-emerald-400 via-teal-500 to-cyan-500 text-base text-white shadow-lg shadow-emerald-700/20">
            GM
          </span>
          GemMarket
        </div>
        <nav className="hidden items-center gap-8 rounded-full border border-slate-200/40 bg-white/70 px-6 py-2 text-sm font-medium text-slate-600 backdrop-blur-xl shadow-sm md:flex">
          {navigation.map((item) => (
            <Link
              key={item.label}
              href={item.href}
              className="transition hover:text-slate-900"
            >
              {item.label}
            </Link>
          ))}
        </nav>
        <div className="hidden items-center gap-3 md:flex">
          <Link
            href="#"
            className="rounded-full border border-slate-200/70 bg-white/60 px-4 py-2 text-sm font-medium text-slate-700 backdrop-blur transition hover:border-slate-300 hover:text-slate-900"
          >
            Log in
          </Link>
          <Link
            href="/seller"
            className="rounded-full bg-gradient-to-r from-emerald-500 via-teal-500 to-cyan-500 px-4 py-2 text-sm font-semibold text-white shadow-lg shadow-emerald-700/30 transition hover:from-emerald-400 hover:via-teal-500 hover:to-cyan-400"
          >
            Become a Seller
          </Link>
        </div>
        <button
          type="button"
          className="inline-flex h-10 w-10 items-center justify-center rounded-full border border-slate-200/60 bg-white/70 text-slate-700 backdrop-blur md:hidden"
          aria-label="Open navigation menu"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="h-5 w-5"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="1.8"
          >
            <path strokeLinecap="round" strokeLinejoin="round" d="M5 7h14M5 12h14M5 17h14" />
          </svg>
        </button>
      </div>
    </header>
  );
}

