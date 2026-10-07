import axios from 'axios';
import React, { useEffect, useState } from 'react'
import type { Empleado } from '../interfaces/Empleado';
import { NumericFormat } from 'react-number-format';
import { departamentosLegibles } from '../constantes/departamento';

export default function ListadoEmpleados() {

    const urlBase = "http://localhost:8080/api/empleados/";

    const [empleados, setEmpleados] = useState<Empleado[]>([]);

    useEffect(() => {
        cargarEmpleados();
    }, []);

    const cargarEmpleados = async () => {
        const resultado = await axios.get(urlBase);
        console.log("Resultado de cargar empleados");
        console.log(resultado.data);
        setEmpleados(resultado.data);
    }

    return (
        <div className="container">
            <div className="container text-center" style={{ margin: '30px' }}>
                <h3>Sistema de Recursos Humanos</h3>
            </div>

            <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Empleado</th>
                        <th scope="col">Departamento</th>
                        <th scope="col">Sueldo</th>
                    </tr>
                </thead>
                <tbody>
                    {
                    //Iterando sobre el arreglo de empleados
                    empleados.map((empleado) => (
                        <tr key={empleado.idEmpleado}>
                            <th scope="row">{empleado.idEmpleado}</th>
                            <td>{empleado.nombreEmpleado}</td>
                            <td>{departamentosLegibles[empleado.departamentoEmpleado]}</td>
                            <td><NumericFormat value={empleado.sueldo}
                                displayType={'text'}
                                thousandSeparator="," prefix={'$'}
                                decimalScale={2} fixedDecimalScale/>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </div>
    )
}