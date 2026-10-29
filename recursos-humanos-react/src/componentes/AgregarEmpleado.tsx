import { Link, useNavigate } from "react-router";
import { departamentosLegibles } from "../constantes/Departamento";
import { useState, type ChangeEvent, type SubmitEvent } from "react";
import type { Empleado } from "../interfaces/Empleado";
import { agregarEmpleado } from "../servicios/empleadoService";
import axios from "axios";


export default function AgregarEmpleado() {

    const navegacion = useNavigate();

    const [enviando, setEnviando] = useState<boolean>(false);

    const [empleado, setEmpleado] = useState<Empleado>({
        nombreEmpleado: '',
        departamentoEmpleado: '',
        sueldo: 0
    });

    const [mensajeError, setMensajeError] = useState<string>('');

    const{nombreEmpleado, departamentoEmpleado, sueldo} = empleado;

    const onInputChange = (e: ChangeEvent<HTMLInputElement | HTMLSelectElement, HTMLInputElement | HTMLSelectElement>) => {
        //spread operator ... (expandir los atributos
        const {name, value} = e.target;
        setEmpleado(previo => ({...previo, [name]: name === 'sueldo' ? Number(value) : value}));
    }

    const onSubmit = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        setEnviando(true); // desactivar el botón de envío para evitar envíos múltiples
        setMensajeError(''); // Limpiar mensaje de error antes de enviar la solicitud
        const empleadoEnviar = {
            ...empleado,
            departamentoEmpleado: empleado.departamentoEmpleado || undefined // Convertir cadena vacía a undefined si no se selecciona un departamento
        }
        try {
            await agregarEmpleado(empleadoEnviar);
            //Redireccionar a la pagina principal
            navegacion("/");
        } catch (error) {
            let mensaje = '';
            if (axios.isAxiosError(error) && error.response) {
                mensaje = error.response.data?.mensaje ?? "Error inesperado y la solicitud no pudo ser procesada";
            } else {
                mensaje= "No se pudo conectar con el servidor";   
            }
            setMensajeError(mensaje);
            console.error(mensaje); 
            
        } finally {
            setEnviando(false); // Reactivar el botón de envío después de la solicitud
        }
    }

    return (
        <div className="container">
            <div className="container text-center" style={{ margin: '30px' }}>
                <h3>Agregar Empleado</h3>
            </div>
            {mensajeError && (
                <div className="container text-center" style={{ margin: '30px' }}>
                    <div className="alert alert-danger alert-dismissible fade show">
                        <button type="button" className="btn-close" onClick={() => setMensajeError('')}>
                        </button>
                        <strong>{mensajeError}</strong>
                    
                    </div>
                </div>
            )}

            <form onSubmit={onSubmit}>
                <div className="mb-3">
                    <label htmlFor="nombre" className="form-label">Nombre</label>
                    <input type="text" className="form-control" id="nombre" name='nombreEmpleado' required={true}
                    value={nombreEmpleado} onChange={onInputChange}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="departamento" className="form-label">Departamento</label>
                    <select className="form-select" id="departamento" name='departamentoEmpleado' value={departamentoEmpleado} onChange={onInputChange}>
                        <option value=''>Sin Asignar</option>
                        {Object.entries(departamentosLegibles).map(([key, value]) => (
                            <option key={key} value={key}>
                                {value}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="sueldo" className="form-label">Sueldo</label>
                    <input type="number" step="any" className="form-control" id="sueldo" name='sueldo' min={0} value={sueldo} onChange={onInputChange}/>
                </div>
                <div className="text-center">
                    <button type="submit" className="btn btn-warning btn-sm me-3" disabled={enviando}>
                        {enviando ? 'Enviando...' : 'Agregar'}
                    </button>
                    <Link to="/" className="btn btn-danger btn-sm">Regresar</Link>
                </div>
                
            </form>
        </div>
    )
}
