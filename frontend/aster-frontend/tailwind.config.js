/** @type {import('tailwindcss').Config} */
export default {
	content: [
		'./index.html',
		'./src/**/*.{js,ts,jsx,tsx}'
	],
	theme: {
		extend: {
			colors: {
				brand: {
					rose: 'var(--brand-rose)',
					lavender: 'var(--brand-lavender)',
					blue: 'var(--brand-blue)',
					green: 'var(--brand-green)'
				},
				content: {
					primary: 'var(--content-primary)',
					secondary: 'var(--content-secondary)',
					inverse: 'var(--content-inverse)'
				},
				background: {
					DEFAULT: "var(--background-fixed-white)",
					"fixed-white": "var(--background-fixed-white)",
					aurora: "var(--background-aurora)",
				}
			},
			borderRadius: {
				lg: '3rem',
				md: '1.5rem',
				sm: '0.75rem'
			}
		}
	}
}
