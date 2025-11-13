"use client";

import { useState } from "react";

type FieldError = {
  field: string;
  message: string;
};

const initialState = {
  fullName: "",
  email: "",
  phone: "",
  businessName: "",
  nicNumber: "",
  gemRegNumber: "",
  address: "",
  message: "",
  nicFile: null as File | null,
  gemRegFile: null as File | null,
};

export function SellerApplicationForm() {
  const [formState, setFormState] = useState(initialState);
  const [submitting, setSubmitting] = useState(false);
  const [submitted, setSubmitted] = useState(false);
  const [errors, setErrors] = useState<FieldError[]>([]);

  const validate = () => {
    const currentErrors: FieldError[] = [];
    if (!formState.fullName.trim()) {
      currentErrors.push({ field: "fullName", message: "Full name is required." });
    }
    if (!formState.email.trim()) {
      currentErrors.push({ field: "email", message: "Email is required." });
    }
    if (!formState.phone.trim()) {
      currentErrors.push({ field: "phone", message: "Contact number is required." });
    }
    if (!formState.nicNumber.trim()) {
      currentErrors.push({ field: "nicNumber", message: "NIC number is required." });
    }
    if (!formState.nicFile) {
      currentErrors.push({ field: "nicFile", message: "NIC scan is required." });
    }
    if (!formState.gemRegFile) {
      currentErrors.push({ field: "gemRegFile", message: "GemReg certificate is required." });
    }
    return currentErrors;
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = event.target;
    setFormState((prev) => ({ ...prev, [name]: value }));
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, files } = event.target;
    if (!files || files.length === 0) return;
    const file = files[0];
    setFormState((prev) => ({ ...prev, [name]: file }));
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const validationErrors = validate();
    if (validationErrors.length > 0) {
      setErrors(validationErrors);
      return;
    }

    setErrors([]);
    setSubmitting(true);
    try {
      await new Promise((resolve) => setTimeout(resolve, 1200));
      setSubmitted(true);
      setFormState(initialState);
    } finally {
      setSubmitting(false);
    }
  };

  const getError = (field: string) => errors.find((error) => error.field === field)?.message;

  return (
    <form
      onSubmit={handleSubmit}
      className="mx-auto max-w-4xl space-y-10 rounded-[32px] border border-slate-200 bg-white/80 p-10 shadow-[0_40px_160px_-90px_rgba(16,185,129,0.45)] backdrop-blur"
    >
      <div className="grid gap-6 md:grid-cols-2">
        <div className="space-y-2">
          <label htmlFor="fullName" className="text-sm font-medium text-slate-700">
            Full name *
          </label>
          <input
            id="fullName"
            name="fullName"
            value={formState.fullName}
            onChange={handleChange}
            placeholder="e.g. Lakshika Fernando"
            className="h-12 rounded-xl border border-slate-200 px-4 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
          />
          {getError("fullName") && <p className="text-xs text-rose-500">{getError("fullName")}</p>}
        </div>
        <div className="space-y-2">
          <label htmlFor="email" className="text-sm font-medium text-slate-700">
            Email *
          </label>
          <input
            id="email"
            name="email"
            type="email"
            value={formState.email}
            onChange={handleChange}
            placeholder="you@example.com"
            className="h-12 rounded-xl border border-slate-200 px-4 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
          />
          {getError("email") && <p className="text-xs text-rose-500">{getError("email")}</p>}
        </div>
        <div className="space-y-2">
          <label htmlFor="phone" className="text-sm font-medium text-slate-700">
            Contact number *
          </label>
          <input
            id="phone"
            name="phone"
            value={formState.phone}
            onChange={handleChange}
            placeholder="+94 77 123 4567"
            className="h-12 rounded-xl border border-slate-200 px-4 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
          />
          {getError("phone") && <p className="text-xs text-rose-500">{getError("phone")}</p>}
        </div>
        <div className="space-y-2">
          <label htmlFor="businessName" className="text-sm font-medium text-slate-700">
            Business / trading name
          </label>
          <input
            id="businessName"
            name="businessName"
            value={formState.businessName}
            onChange={handleChange}
            placeholder="Registered business entity"
            className="h-12 rounded-xl border border-slate-200 px-4 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
          />
        </div>
      </div>

      <div className="grid gap-6 md:grid-cols-2">
        <div className="space-y-2">
          <label htmlFor="nicNumber" className="text-sm font-medium text-slate-700">
            NIC / Passport number *
          </label>
          <input
            id="nicNumber"
            name="nicNumber"
            value={formState.nicNumber}
            onChange={handleChange}
            placeholder="NIC number used for verification"
            className="h-12 rounded-xl border border-slate-200 px-4 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
          />
          {getError("nicNumber") && <p className="text-xs text-rose-500">{getError("nicNumber")}</p>}
        </div>
        <div className="space-y-2">
          <label htmlFor="gemRegNumber" className="text-sm font-medium text-slate-700">
            GemReg license number
          </label>
          <input
            id="gemRegNumber"
            name="gemRegNumber"
            value={formState.gemRegNumber}
            onChange={handleChange}
            placeholder="Registered GemReg identifier"
            className="h-12 rounded-xl border border-slate-200 px-4 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
          />
        </div>
      </div>

      <div className="grid gap-6 lg:grid-cols-2">
        <div className="space-y-2">
          <label htmlFor="nicFile" className="text-sm font-medium text-slate-700">
            Upload NIC scan *
          </label>
          <div className="flex h-36 flex-col items-center justify-center gap-2 rounded-2xl border border-dashed border-slate-300 bg-slate-50/60 px-4 text-center">
            <input
              id="nicFile"
              name="nicFile"
              type="file"
              accept="image/*,.pdf"
              onChange={handleFileChange}
              className="hidden"
            />
            <label
              htmlFor="nicFile"
              className="cursor-pointer rounded-full bg-slate-900 px-4 py-2 text-xs font-semibold text-white transition hover:bg-slate-800"
            >
              Upload NIC file
            </label>
            <p className="text-xs text-slate-500">PNG, JPG or PDF up to 10MB</p>
            {formState.nicFile && (
              <p className="text-xs font-medium text-slate-600">{formState.nicFile.name}</p>
            )}
            {getError("nicFile") && <p className="text-xs text-rose-500">{getError("nicFile")}</p>}
          </div>
        </div>
        <div className="space-y-2">
          <label htmlFor="gemRegFile" className="text-sm font-medium text-slate-700">
            Upload GemReg certificate *
          </label>
          <div className="flex h-36 flex-col items-center justify-center gap-2 rounded-2xl border border-dashed border-slate-300 bg-slate-50/60 px-4 text-center">
            <input
              id="gemRegFile"
              name="gemRegFile"
              type="file"
              accept="image/*,.pdf"
              onChange={handleFileChange}
              className="hidden"
            />
            <label
              htmlFor="gemRegFile"
              className="cursor-pointer rounded-full bg-slate-900 px-4 py-2 text-xs font-semibold text-white transition hover:bg-slate-800"
            >
              Upload certificate
            </label>
            <p className="text-xs text-slate-500">PNG, JPG or PDF up to 10MB</p>
            {formState.gemRegFile && (
              <p className="text-xs font-medium text-slate-600">{formState.gemRegFile.name}</p>
            )}
            {getError("gemRegFile") && <p className="text-xs text-rose-500">{getError("gemRegFile")}</p>}
          </div>
        </div>
      </div>

      <div className="space-y-2">
        <label htmlFor="address" className="text-sm font-medium text-slate-700">
          Registered address
        </label>
        <textarea
          id="address"
          name="address"
          value={formState.address}
          onChange={handleChange}
          placeholder="Include commercial address and any showroom locations."
          rows={3}
          className="rounded-xl border border-slate-200 px-4 py-3 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
        />
      </div>

      <div className="space-y-2">
        <label htmlFor="message" className="text-sm font-medium text-slate-700">
          Tell us about your inventory
        </label>
        <textarea
          id="message"
          name="message"
          value={formState.message}
          onChange={handleChange}
          placeholder="Share gem types, sourcing details, average order value, and any existing distribution channels."
          rows={4}
          className="rounded-xl border border-slate-200 px-4 py-3 text-sm text-slate-900 outline-none transition focus:border-emerald-300 focus:ring-2 focus:ring-emerald-200/60"
        />
      </div>

      <div className="flex flex-col gap-4 rounded-2xl border border-slate-200/80 bg-slate-50/80 px-6 py-5 text-sm text-slate-600">
        <div className="flex items-start gap-3">
          <svg
            className="mt-1 h-4 w-4 text-emerald-500"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="1.5"
          >
            <path strokeLinecap="round" strokeLinejoin="round" d="M5 13l4 4L19 7" />
          </svg>
          <p>
            Applications are reviewed within 24 hours. Our compliance team may reach out for additional
            supporting documents before activating your seller dashboard.
          </p>
        </div>
        <div className="flex items-start gap-3">
          <svg
            className="mt-1 h-4 w-4 text-emerald-500"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="1.5"
          >
            <path strokeLinecap="round" strokeLinejoin="round" d="M12 6v6l3 3" />
          </svg>
          <p>
            While your account is under review, you can prepare listings, organize media, and select a subscription
            plan. We activate billing only after approval.
          </p>
        </div>
      </div>

      <div className="flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between">
        <button
          type="submit"
          className="inline-flex items-center justify-center rounded-full bg-gradient-to-r from-emerald-500 via-teal-500 to-cyan-500 px-6 py-3 text-sm font-semibold text-white shadow-lg shadow-emerald-500/30 transition hover:-translate-y-0.5 hover:from-emerald-400 hover:via-teal-500 hover:to-cyan-400 disabled:cursor-not-allowed disabled:opacity-60"
          disabled={submitting}
        >
          {submitting ? "Submitting application..." : "Submit seller application"}
        </button>
        {submitted && (
          <p className="text-sm font-medium text-emerald-600">
            Thank you! Your application has been received. Our onboarding team will contact you soon.
          </p>
        )}
      </div>
    </form>
  );
}


