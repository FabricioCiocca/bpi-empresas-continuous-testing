package com.everis.models.bpi;


public class PagosServiciosData {
    private String montoIncial;
    private String cantdni;
    private String simbolo;
    private String rol;
    private String ambiente;
    private String usuario;
    private String cuentaOrigen;
    private String empresa;
    private String servicio;
    private String dniPagador;
    private String monto;
    private String descripcion;
    private String password;
    private String cuotasPagar;
    private String tipoDeCuenta;
    private Double saldoActual;
    private String nroSolicitudProcess;
    private String estado;
    private String fechaHora;
    private String nombreusuario;
    private String tipoDeCambioVenta;
    private String tipoDeCambioCompra;
    private String saldoInicial;
    private String saldoActualizado;
    private String estadoSolicitudDetalle;
    private String MontoProcess;
    private String DatosCargoDetalle;
    private String modoPago;
    private String tipoCuenta2SinData;
    private String cuentaOrigen2SinData;
    private String servicio2SinData;

    private String empresa2DataMaestra;
    private String servicio2DataMaestra;

    private String itf;
    private String montoSolaFirma;
    private String montoFirmaConjunta;
    private String Company;
    private String NumeroOperacion;
    private String FechaRecaudacion;

    public String getFechaRecaudacion() {
        return FechaRecaudacion;
    }

    public void setFechaRecaudacion(String fechaRecaudacion) {
        FechaRecaudacion = fechaRecaudacion;
    }

    public String getDetallerecaudacion() {
        return detallerecaudacion;
    }

    public void setDetallerecaudacion(String detallerecaudacion) {
        this.detallerecaudacion = detallerecaudacion;
    }

    private String detallerecaudacion;

    public String getNumeroOperacion() {
        return NumeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        NumeroOperacion = numeroOperacion;
    }

    public String getCredencialOk() {
        return CredencialOk;
    }

    public void setCredencialOk(String credencialOk) {
        CredencialOk = credencialOk;
    }

    private String CredencialOk;
    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getItf() {
        return itf;
    }

    public void setItf(String itf) {
        this.itf = itf;
    }

    public String getServicio2SinData() {
        return servicio2SinData;
    }

    public void setServicio2SinData(String servicio2SinData) {
        this.servicio2SinData = servicio2SinData;
    }

    public String getTipoCuenta2SinData() {
        return tipoCuenta2SinData;
    }

    public void setTipoCuenta2SinData(String tipoCuenta2SinData) {
        this.tipoCuenta2SinData = tipoCuenta2SinData;
    }

    public String getCuentaOrigen2SinData() {
        return cuentaOrigen2SinData;
    }

    public void setCuentaOrigen2SinData(String cuentaOrigen2SinData) {
        this.cuentaOrigen2SinData = cuentaOrigen2SinData;
    }

    public String getMontoIncial() {
        return montoIncial;
    }

    public void setMontoIncial(String montoIncial) {
        this.montoIncial = montoIncial;
    }

    public String getCantdni() {
        return cantdni;
    }

    public void setCantdni(String cantdni) {
        this.cantdni = cantdni;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getModoPago() {
        return modoPago;
    }

    public void setModoPago(String modoPago) {
        this.modoPago = modoPago;
    }

    public String getMontoProcess() {
        return MontoProcess;
    }

    public void setMontoProcess(String montoProcess) {
        MontoProcess = montoProcess;
    }

    public String getEstadoSolicitudDetalle() {
        return estadoSolicitudDetalle;
    }

    public void setEstadoSolicitudDetalle(String estadoSolicitudDetalle) {
        this.estadoSolicitudDetalle = estadoSolicitudDetalle;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getNroSolicitudProcess() {
        return nroSolicitudProcess;
    }

    public void setNroSolicitudProcess(String nroSolicitudProcess) {
        this.nroSolicitudProcess = nroSolicitudProcess;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }


    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDniPagador() {
        return dniPagador;
    }

    public void setDniPagador(String dniPagador) {
        this.dniPagador = dniPagador;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public void setTipoDeCuenta(String tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }

    public String getCuotasPagar() {
        return cuotasPagar;
    }

    public void setCuotasPagar(String cuotasPagar) {
        this.cuotasPagar = cuotasPagar;
    }


    public String getDatosCargoDetalle() {
        return DatosCargoDetalle;
    }

    public void setDatosCargoDetalle(String datosCargoDetalle) {
        DatosCargoDetalle = datosCargoDetalle;
    }

    public String getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(String saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getSaldoActualizado() {
        return saldoActualizado;
    }

    public void setSaldoActualizado(String saldoActualizado) {
        this.saldoActualizado = saldoActualizado;
    }

    public String getTipoDeCambioVenta() {
        return tipoDeCambioVenta;
    }

    public void setTipoDeCambioVenta(String tipoDeCambioVenta) {
        this.tipoDeCambioVenta = tipoDeCambioVenta;
    }

    public String getTipoDeCambioCompra() {
        return tipoDeCambioCompra;
    }

    public void setTipoDeCambioCompra(String tipoDeCambioCompra) {
        this.tipoDeCambioCompra = tipoDeCambioCompra;
    }


    public String getEmpresa2DataMaestra() {
        return empresa2DataMaestra;
    }

    public void setEmpresa2DataMaestra(String empresa2DataMaestra) {
        this.empresa2DataMaestra = empresa2DataMaestra;
    }

    public String getServicio2DataMaestra() {
        return servicio2DataMaestra;
    }

    public void setServicio2DataMaestra(String servicio2DataMaestra) {
        this.servicio2DataMaestra = servicio2DataMaestra;
    }

    private String montosPagados;
    public String getmontosPagados() {
        return montosPagados;
    }
    public void setmontosPagados(String montosPagados) {
        this.montosPagados = montosPagados;
    }

    private String montoPagado;
    public String getmontoPagado() {
        return montoPagado;
    }
    public void setmontoPagado(String montosPagado) {
        this.montoPagado = montosPagado;
    }

    private String codigo;
    public String getcodigo() {
        return codigo;
    }
    public void setcodigo(String codigo) {
        this.codigo = codigo;
    }

    private String TipoDePago;
    public String getTipoDePago() {
        return TipoDePago;
    }
    public void setTipoDePago(String TipoDePago) {
        this.TipoDePago = TipoDePago;
    }

    private String montoTipoCambio;
    public String getmontoTipoCambio() {
        return montoTipoCambio;
    }
    public void setmontoTipoCambio(String montoTipoCambio) {
        this.montoTipoCambio = montoTipoCambio;
    }

    private String montoPagadoTipoCambio;
    public String getmontoPagadoTipoCambio() {
        return montoPagadoTipoCambio;
    }
    public void setmontoPagadoTipoCambio(String montoPagadoTipoCambio) {
        this.montoPagadoTipoCambio = montoPagadoTipoCambio;
    }

    private String saldoFinal;
    public String getsaldoFinal() {
        return saldoFinal;
    }
    public void setsaldoFinal(String saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    private String modoDePago;
    public String getmodoDePago() {
        return modoDePago;
    }
    public void setmodoDePago(String modoDePago) {
        this.modoDePago = modoDePago;
    }

    private String importeM;
    public String getimporteM() {
        return importeM;
    }
    public void setimporteM(String importeM) {
        this.importeM = importeM;
    }

    private String descricionM;
    public String getdescricionM() {
        return descricionM;
    }
    public void setdescricionM(String descricionM) {
        this.descricionM = descricionM;
    }

    private String movimientoM;
    public String getmovimientoM() {
        return movimientoM;
    }
    public void setmovimientoM(String movimientoM) {
        this.movimientoM = movimientoM;
    }

    private String fechaOperacionM;

    public String getMontoSolaFirma() {
        return montoSolaFirma;
    }

    public void setMontoSolaFirma(String montoSolaFirma) {
        this.montoSolaFirma = montoSolaFirma;
    }

    public String getMontoFirmaConjunta() {
        return montoFirmaConjunta;
    }

    public void setMontoFirmaConjunta(String montoFirmaConjunta) {
        this.montoFirmaConjunta = montoFirmaConjunta;
    }

    public String getfechaOperacionM() {
        return fechaOperacionM;
    }
    public void setfechaOperacionM(String fechaOperacionM) {
        this.fechaOperacionM = fechaOperacionM;
    }

    private String cantAutorizacion;
    public String getcantAutorizacion() {
        return cantAutorizacion;
    }
    public void setcantAutorizacion(String cantAutorizacion) {
        this.cantAutorizacion = cantAutorizacion;
    }

    private String FechaHora2;
    public String getFechaHora2() {
        return FechaHora2;
    }
    public void setFechaHora2(String FechaHora2) {
        this.FechaHora2 = FechaHora2;
    }

    private String credencialIncorrecto;
    public String getcredencialIncorrecto() {
        return credencialIncorrecto;
    }
    public void setcredencialIncorrecto(String credencialIncorrecto) {
        this.credencialIncorrecto = credencialIncorrecto;
    }

}
