import axios from "axios";
import type { Empleado } from "../interfaces/Empleado";

const urlBase = "http://localhost:8080/api/empleados/";

export const obtenerEmpleados = () => axios.get<Empleado[]>(urlBase);