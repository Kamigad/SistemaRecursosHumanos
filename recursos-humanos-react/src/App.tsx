import { BrowserRouter, Route, Routes } from "react-router";
import ListadoEmpleados from "./componentes/ListadoEmpleados";
import Navegacion from "./componentes/Navegacion";

function App() {

  return (
    <div className="container">
      <BrowserRouter>
      <Navegacion/>
      <Routes>
        <Route path="/" element={<ListadoEmpleados/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App
