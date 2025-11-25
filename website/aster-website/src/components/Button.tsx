export default function Button({ variant, label, px, onClick }: { variant: string; label: string; px?: number; onClick?: () => void }) {
  const variants: Record<string, string> = {
    white: "h-8 bg-[var(--background-fixed-white)] text-sm text-[var(--content-primary)] shadow-sm font-semibold px-4 rounded-full hover:cursor-pointer",
    black: "h-8 bg-[var(--background-fixed-black)] text-sm text-[var(--content-inverse)] shadow-sm font-semibold px-4 rounded-full hover:cursor-pointer",
    "clear-white": "h-8 text-sm text-[var(--content-inverse)] font-semibold px-1 hover:cursor-pointer",
    "clear-black": "h-8 text-sm text-[var(--content-primary)] font-semibold px-1 hover:cursor-pointer",
  }

  const classes = variants[variant] ?? variants["white"]
  const pxClass = px ? `px-${px}` : ""

  return <button className={`${classes} ${pxClass}`} onClick={onClick}>{label}</button>
}
