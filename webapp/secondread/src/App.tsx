import './globals.css';
import { Routes, Route } from "react-router-dom";
import { Home } from "./_root/pages";
import AuthLayout from './_auth/AuthLayout';
import LogInForm from "./_auth/forms/LogInForm";
import RegistrationForm from './_auth/forms/RegistrationForm';
import RootLayout from './_root/RootLayout';


function App() {
  return (
    <main className="flex h-screen">
      <Routes>
        {/* public */}
        <Route element={<AuthLayout />} >
          <Route path="/sign-in" element={<RegistrationForm />} />
          <Route path="/login" element={<LogInForm />} />
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