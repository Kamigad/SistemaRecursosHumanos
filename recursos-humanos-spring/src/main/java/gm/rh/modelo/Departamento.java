package gm.rh.modelo;

public enum Departamento {
    SISTEMAS("Sistemas"),
    TI("Ti"),
    VENTAS("Ventas"),
    CONTABILIDAD("Contabilidad"),
    RECURSOS_HUMANOS("Recursos Humanos"),
    MERCADOTECNIA("Mercadotecnia");

    private String descripcion;

    Departamento(String departamentoEmpleado){
        this.descripcion = departamentoEmpleado;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
