// Set current year in footer
document.addEventListener('DOMContentLoaded', function() {
    const yearElement = document.getElementById('currentYear');
    if (yearElement) {
        yearElement.textContent = new Date().getFullYear();
    }

    // Mobile menu toggle
    const navToggle = document.getElementById('navToggle');
    const navMenu = document.getElementById('navMenu');
    const mobileMenuBtn = document.getElementById('mobileMenuBtn');
    
    if (navToggle && navMenu) {
        navToggle.addEventListener('click', function() {
            navMenu.classList.toggle('active');
            this.classList.toggle('active');
        });
    }
    
    // Handle old mobile menu button (for index.html)
    if (mobileMenuBtn && navMenu) {
        mobileMenuBtn.addEventListener('click', function() {
            navMenu.style.display = navMenu.style.display === 'flex' ? 'none' : 'flex';
        });
    }

    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            const href = this.getAttribute('href');
            if (href === '#' || href === '#!') return;
            
            const target = document.querySelector(href);
            if (target) {
                e.preventDefault();
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
                
                // Close mobile menu if open
                if (navMenu && window.innerWidth < 768) {
                    navMenu.classList.remove('active');
                    if (navToggle) navToggle.classList.remove('active');
                }
            }
        });
    });

    // Seller form handling
    const sellerForm = document.getElementById('sellerForm');
    if (sellerForm) {
        const formInputs = sellerForm.querySelectorAll('input, textarea');
        const fileInputs = sellerForm.querySelectorAll('input[type="file"]');
        const loginNotice = document.getElementById('sellerLoginNotice');
        const submitBtn = document.getElementById('submitBtn');
        const submitText = document.getElementById('submitText');
        const successMessage = document.getElementById('successMessage');
        const generalError = document.getElementById('sellerFormError');

        const activeUser = (() => {
            try {
                return JSON.parse(sessionStorage.getItem('userInfo'));
            } catch (error) {
                return null;
            }
        })();

        const isAuthenticated = !!(activeUser && activeUser.id);

        if (loginNotice) {
            loginNotice.style.display = isAuthenticated ? 'none' : 'flex';
        }
        
        // Handle file input changes
        fileInputs.forEach(input => {
            input.addEventListener('change', function() {
                const fileName = this.files[0]?.name;
                const fileNameElement = document.getElementById(this.id + 'Name');
                if (fileNameElement && fileName) {
                    fileNameElement.textContent = fileName;
                } else if (fileNameElement && !fileName) {
                    fileNameElement.textContent = 'No file chosen';
                }
            });
        });

        // Form submission
        sellerForm.addEventListener('submit', function(e) {
            e.preventDefault();

            // Clear previous errors
            document.querySelectorAll('.form-error').forEach(error => {
                error.classList.remove('show');
                error.textContent = '';
            });
            if (generalError) {
                generalError.textContent = '';
                generalError.classList.remove('show');
            }
            
            // Get form data
            const formData = new FormData(sellerForm);
            const errors = [];
            
            // Validate required fields
            const fullName = formData.get('fullName')?.trim();
            const email = formData.get('email')?.trim();
            const phone = formData.get('phone')?.trim();
            const nicNumber = formData.get('nicNumber')?.trim();
            const nicFrontFile = formData.get('nicFrontFile');
            const nicBackFile = formData.get('nicBackFile');
            const gemRegFile = formData.get('gemRegFile');
            
            if (!fullName) {
                errors.push({ field: 'fullName', message: 'Full name is required.' });
            }
            
            if (!email) {
                errors.push({ field: 'email', message: 'Email is required.' });
            } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
                errors.push({ field: 'email', message: 'Please enter a valid email address.' });
            }
            
            if (!phone) {
                errors.push({ field: 'phone', message: 'Contact number is required.' });
            }
            
            if (!nicNumber) {
                errors.push({ field: 'nicNumber', message: 'NIC number is required.' });
            }
            
            if (!nicFrontFile || nicFrontFile.size === 0) {
                errors.push({ field: 'nicFrontFile', message: 'NIC front image is required.' });
            }
            
            if (!nicBackFile || nicBackFile.size === 0) {
                errors.push({ field: 'nicBackFile', message: 'NIC back image is required.' });
            }
            
            // Display field errors
            if (errors.length > 0) {
                errors.forEach(error => {
                    const errorElement = document.getElementById(error.field + 'Error');
                    if (errorElement) {
                        errorElement.textContent = error.message;
                        errorElement.classList.add('show');
                    }
                });
                return;
            }
            
            // Disable submit button
            submitBtn.disabled = true;
            submitText.textContent = 'Submitting application...';
            
            const payload = new FormData();
            payload.append('userId', activeUser?.id ?? '');
            payload.append('fullName', fullName);
            payload.append('email', email);
            payload.append('phoneNumber', phone);
            payload.append('businessName', formData.get('businessName')?.trim() || '');
            payload.append('nicNumber', nicNumber);
            payload.append('gemRegNumber', formData.get('gemRegNumber')?.trim() || '');
            payload.append('address', formData.get('address')?.trim() || '');
            payload.append('notes', formData.get('message')?.trim() || '');
            if (nicFrontFile) {
                payload.append('nicFrontFile', nicFrontFile);
            }
            if (nicBackFile) {
                payload.append('nicBackFile', nicBackFile);
            }
            if (gemRegFile && gemRegFile.size > 0) {
                payload.append('gemRegFile', gemRegFile);
            }

            fetch('/api/seller-applications', {
                method: 'POST',
                body: payload
            })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errorData => {
                        throw new Error(errorData.error || 'Unable to submit application');
                    }).catch(() => {
                        throw new Error('Unable to submit application');
                    });
                }
                return response.json();
            })
            .then(() => {
                sellerForm.reset();
                document.querySelectorAll('.file-name').forEach(el => {
                    el.textContent = 'No file chosen';
                });

                if (successMessage) {
                    successMessage.textContent = 'Application submitted! Our admin team will review and notify you via email.';
                    successMessage.classList.add('show');
                    successMessage.style.color = 'var(--success)';
                }
            })
            .catch(error => {
                if (generalError) {
                    generalError.textContent = error.message;
                    generalError.classList.add('show');
                }
            })
            .finally(() => {
                submitBtn.disabled = false;
                submitText.textContent = 'Submit Application';
            });
        });
        
        // Clear errors on input
        formInputs.forEach(input => {
            input.addEventListener('input', function() {
                const errorElement = document.getElementById(this.name + 'Error');
                if (errorElement) {
                    errorElement.classList.remove('show');
                    errorElement.textContent = '';
                }
                if (generalError) {
                    generalError.classList.remove('show');
                    generalError.textContent = '';
                }
            });
        });
    }

    // Newsletter form handling
    const newsletterForm = document.querySelector('.newsletter-form');
    if (newsletterForm) {
        const newsletterInput = newsletterForm.querySelector('.newsletter-input');
        const newsletterBtn = newsletterForm.querySelector('.newsletter-btn');
        
        if (newsletterBtn) {
            newsletterBtn.addEventListener('click', function(e) {
                e.preventDefault();
                const email = newsletterInput?.value.trim();
                
                if (email && /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
                    // Simulate subscription
                    alert('Thank you for subscribing! You will receive updates from GemMarket.');
                    if (newsletterInput) {
                        newsletterInput.value = '';
                    }
                } else {
                    alert('Please enter a valid email address.');
                }
            });
        }
    }

    // Add hover effects for interactive elements
    const interactiveElements = document.querySelectorAll('button, a, .feature-card, .workflow-step, .testimonial-card, .featured-card');
    interactiveElements.forEach(element => {
        element.addEventListener('mouseenter', function() {
            this.style.transition = 'all 0.2s ease';
        });
    });

    // Navbar scroll effect
    let lastScroll = 0;
    const navbar = document.querySelector('.nav');
    
    if (navbar) {
        window.addEventListener('scroll', function() {
            const currentScroll = window.pageYOffset;
            
            if (currentScroll > 100) {
                navbar.style.boxShadow = '0 4px 6px -1px rgba(0, 0, 0, 0.3)';
            } else {
                navbar.style.boxShadow = 'none';
            }
            
            lastScroll = currentScroll;
        });
    }

    // Auth-aware navigation state
    const navActionsWrapper = document.querySelector('.nav-actions');

    const parseStoredUser = () => {
        const raw = sessionStorage.getItem('userInfo');
        if (!raw) {
            return null;
        }
        try {
            return JSON.parse(raw);
        } catch (error) {
            console.warn('Unable to parse stored user info', error);
            sessionStorage.removeItem('userInfo');
            return null;
        }
    };

    const handleLogout = () => {
        sessionStorage.removeItem('userInfo');
        sessionStorage.removeItem('sellerInfo');
        window.location.href = 'login.html';
    };

    const ensureDashboardLink = () => {
        if (!navMenu) return;
        // Only show dashboard for sellers
        const sellerInfo = sessionStorage.getItem('sellerInfo');
        if (!sellerInfo) {
            removeDashboardLink();
            return;
        }

        try {
            const seller = JSON.parse(sellerInfo);
            if (seller.verificationStatus !== 'APPROVED') {
                removeDashboardLink();
                return;
            }
        } catch (e) {
            removeDashboardLink();
            return;
        }

        const dashboardLinks = Array.from(navMenu.querySelectorAll('a[href="seller-dashboard.html"], [data-nav="dashboard"]'));
        dashboardLinks.slice(1).forEach(link => link.remove());

        let dashboardLink = dashboardLinks[0];
        if (!dashboardLink) {
            dashboardLink = document.createElement('a');
            dashboardLink.href = 'seller-dashboard.html';
            dashboardLink.textContent = 'Dashboard';
            navMenu.insertBefore(dashboardLink, navMenu.firstChild);
        }

        dashboardLink.dataset.nav = 'dashboard';
        dashboardLink.classList.add('nav-link');
        dashboardLink.style.display = 'inline-flex';
    };

    const removeDashboardLink = () => {
        if (!navMenu) return;
        const dashboardLink = navMenu.querySelector('[data-nav="dashboard"]');
        if (dashboardLink) {
            dashboardLink.remove();
        }
    };

    const ensureLogoutButton = () => {
        if (!navActionsWrapper) return null;

        const candidates = navActionsWrapper.querySelectorAll('[data-auth="logout"], #navLogoutBtn, a[onclick="logout()"]');
        let primary = null;

        candidates.forEach((btn, index) => {
            if (!primary) {
                primary = btn;
            } else {
                btn.remove();
            }
        });

        if (primary) {
            if (primary.tagName === 'A') {
                primary.href = 'javascript:void(0)';
            }
            primary.id = 'navLogoutBtn';
            primary.classList.add('nav-logout-btn');
            primary.dataset.auth = 'logout';
            if (!primary.dataset.logoutBound) {
                primary.addEventListener('click', (event) => {
                    event.preventDefault();
                    handleLogout();
                });
                primary.dataset.logoutBound = 'true';
            }
            return primary;
        }

        const newBtn = document.createElement('button');
        newBtn.type = 'button';
        newBtn.id = 'navLogoutBtn';
        newBtn.className = 'nav-logout-btn';
        newBtn.textContent = 'Log Out';
        newBtn.dataset.auth = 'logout';
        newBtn.dataset.logoutBound = 'true';
        newBtn.addEventListener('click', handleLogout);
        navActionsWrapper.appendChild(newBtn);
        return newBtn;
    };

    const removeLogoutButton = () => {
        const logoutBtn = document.getElementById('navLogoutBtn');
        if (logoutBtn) {
            logoutBtn.remove();
        }
    };

    const setPricingReviewVisibility = (visible) => {
        const targets = document.querySelectorAll('.nav-menu a[href="index.html#testimonials"]');
        targets.forEach(link => {
            link.style.display = visible ? '' : 'none';
        });
    };

    const setBecomeSellerVisibility = (visible) => {
        // Hide "Become Seller" buttons in navigation and throughout the page
        const becomeSellerButtons = document.querySelectorAll('a[href="seller.html"]');
        becomeSellerButtons.forEach(btn => {
            // Check if it's in the nav-actions (navbar) or anywhere else
            if (btn.closest('.nav-actions') || btn.classList.contains('btn-gradient') || btn.classList.contains('btn-hero-primary') || btn.classList.contains('btn-primary-large')) {
                btn.style.display = visible ? '' : 'none';
            }
        });
    };

    const syncNavWithAuth = () => {
        const user = parseStoredUser();
        const sellerInfo = sessionStorage.getItem('sellerInfo');
        const guestButtons = document.querySelectorAll('.nav-actions a[href="signup.html"], .nav-actions a[href="login.html"]');

        if (user) {
            guestButtons.forEach(btn => {
                btn.style.display = 'none';
            });
            ensureDashboardLink();
            const logoutBtn = ensureLogoutButton();
            if (logoutBtn) {
                logoutBtn.style.display = 'inline-flex';
            }
            setPricingReviewVisibility(false);
            
            // Hide "Become Seller" button if user is already a seller
            if (sellerInfo) {
                try {
                    const seller = JSON.parse(sellerInfo);
                    if (seller.verificationStatus === 'APPROVED') {
                        setBecomeSellerVisibility(false);
                    } else {
                        setBecomeSellerVisibility(true);
                    }
                } catch (e) {
                    setBecomeSellerVisibility(true);
                }
            } else {
                setBecomeSellerVisibility(true);
            }
        } else {
            guestButtons.forEach(btn => {
                btn.style.display = '';
            });
            removeDashboardLink();
            removeLogoutButton();
            setPricingReviewVisibility(true);
            setBecomeSellerVisibility(true);
        }
    };

    syncNavWithAuth();
    window.addEventListener('storage', (event) => {
        if (event.key === 'userInfo' || event.key === 'sellerInfo') {
            syncNavWithAuth();
        }
    });
    
    // Expose syncNavWithAuth globally so login pages can call it after setting sellerInfo
    window.syncNavWithAuth = syncNavWithAuth;
});

// Button handlers for index.html
function handleContactSeller() {
    if (confirm('Please sign in to contact sellers. Would you like to go to the login page?')) {
        window.location.href = 'login.html';
    }
}

// Make functions globally available
window.handleContactSeller = handleContactSeller;

// Add click handlers for all contact seller buttons
document.addEventListener('DOMContentLoaded', function() {
    // Handle contact seller buttons that don't have onclick
    document.querySelectorAll('.btn-featured, .btn-card').forEach(button => {
        if (!button.onclick && button.textContent.toLowerCase().includes('contact')) {
            button.addEventListener('click', function(e) {
                e.preventDefault();
                handleContactSeller();
            });
        }
    });
    
});

