import { CTASection } from "@/components/CTASection";
import { FeaturedListings } from "@/components/FeaturedListings";
import { Footer } from "@/components/Footer";
import { HeroSection } from "@/components/HeroSection";
import { MarketplaceHighlights } from "@/components/MarketplaceHighlights";
import { Navbar } from "@/components/Navbar";
import { PremiumPromo } from "@/components/PremiumPromo";
import { SellerWorkflow } from "@/components/SellerWorkflow";
import { SubscriptionPlans } from "@/components/SubscriptionPlans";
import { Testimonials } from "@/components/Testimonials";
import { TrustedBy } from "@/components/TrustedBy";

export default function Home() {
  return (
    <div className="bg-slate-50 text-slate-900">
      <Navbar />
      <main className="space-y-0">
        <HeroSection />
        <TrustedBy />
        <MarketplaceHighlights />
        <FeaturedListings />
        <SellerWorkflow />
        <PremiumPromo />
        <SubscriptionPlans />
        <Testimonials />
        <CTASection />
      </main>
      <Footer />
    </div>
  );
}
