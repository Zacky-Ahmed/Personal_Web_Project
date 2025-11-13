export function Footer() {
  return (
    <footer className="border-t border-slate-200/70 bg-white py-12">
      <div className="mx-auto flex max-w-6xl flex-col gap-8 px-6 text-sm text-slate-600 lg:flex-row lg:items-center lg:justify-between">
        <div className="flex flex-col gap-4">
          <div className="flex items-center gap-3 text-slate-900">
            <span className="grid h-10 w-10 place-content-center rounded-2xl bg-gradient-to-br from-emerald-400 via-teal-500 to-cyan-500 text-base font-semibold text-white shadow-lg shadow-emerald-700/20">
              GM
            </span>
            <div>
              <p className="text-base font-semibold">GemMarket</p>
              <p className="text-xs text-slate-500">Certified gemstone marketplace</p>
            </div>
          </div>
          <p className="max-w-sm text-sm text-slate-500">
            Connecting gem experts with global buyers through authenticity, transparency, and premium concierge support.
          </p>
        </div>
        <nav className="flex flex-wrap gap-x-8 gap-y-3 text-sm font-medium text-slate-600">
          <a href="#" className="transition hover:text-slate-900">
            Privacy Policy
          </a>
          <a href="#" className="transition hover:text-slate-900">
            Terms of Service
          </a>
          <a href="#" className="transition hover:text-slate-900">
            Seller Handbook
          </a>
          <a href="#" className="transition hover:text-slate-900">
            Support
          </a>
        </nav>
        <div className="space-y-3 text-sm text-slate-500">
          <p className="font-medium text-slate-700">Stay in the loop</p>
          <div className="flex flex-wrap gap-3">
            <input
              type="email"
              placeholder="Email address"
              className="h-10 rounded-full border border-slate-200 px-4 text-sm text-slate-700 outline-none transition focus:border-emerald-300"
            />
            <button className="inline-flex items-center justify-center rounded-full bg-slate-900 px-4 py-2 text-sm font-semibold text-white transition hover:bg-slate-800">
              Subscribe
            </button>
          </div>
        </div>
      </div>
      <div className="mt-10 border-t border-slate-200/70">
        <div className="mx-auto flex max-w-6xl flex-col gap-4 px-6 py-6 text-xs text-slate-500 sm:flex-row sm:items-center sm:justify-between">
          <p>© {new Date().getFullYear()} GemMarket. All rights reserved.</p>
          <div className="flex items-center gap-4">
            <span>hello@gemmarket.app</span>
            <span>+94 77 123 4567</span>
          </div>
        </div>
      </div>
    </footer>
  );
}

