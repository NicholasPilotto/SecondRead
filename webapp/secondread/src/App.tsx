import './globals.css';
import { Routes, Route } from "react-router-dom";
import { Home } from "./_root/pages";
import AuthLayout from './_auth/AuthLayout';
import LogInForm from "./_auth/forms/LogInForm";
import SignInForm from "./_auth/forms/SignInForm";
import RootLayout from './_root/RootLayout';


function App() {
  return (
    <main className="flex h-screen">
      <Routes>
        {/* public */}
        <Route element={<AuthLayout />} >
          <Route path="/sign-in" element={<SignInForm />} />
          <Route path="/log-in" element={<LogInForm />} />
        </Route>

        {/* private */}
        <Route element={<RootLayout />}>
          <Route index element={<Home />} />
        </Route>
      </Routes>
    </main>
  )
}

export default App