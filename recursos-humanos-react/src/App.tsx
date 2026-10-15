import { BrowserRouter, Route, Routes } from "react-router";
import ListadoEmpleados from "./componentes/ListadoEmpleados";
import Navegacion from "./componentes/Navegacion";
import AgregarEmpleado from "./componentes/AgregarEmpleado";

function App() {

  return (
    <div className="container">
      <BrowserRouter>
      <Navegacion/>
      <Routes>
        <Route path="/" element={<ListadoEmpleados/>}/>
        <Route path="/agregar" element={<AgregarEmpleado/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App
