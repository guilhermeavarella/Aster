import { LiquidGlassReact } from 'solid-glass/react';
import "solid-glass/style.css";


function App() {  
  return (
    <div className="w-full h-full min-h-[200vh] min-w-screen flex flex-row items-start justify-start bg-gradient-to-bl from-[var(--brand-pink)] via-[var(--brand-lavender)] to-[var(--brand-blue)]">  
      <div className="w-full h-full max-w-92 flex flex-row items-start justify-center p-6">
        <LiquidGlassReact
      width="400px"
      height="300px"
      tintColor="[var(--fixed-white)]"
      tintOpacity={0.05}
      frostBlur={50}
      className="p-6"
    >
      <h1>Your Content Here</h1>
    </LiquidGlassReact>
      </div>
      <div className="w-full h-full pt-6 pb-6 pl-3 pr-9">

      </div>
    </div>
  )
}

export default App
