package ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaResponseDTO {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldo;
    private String fechaApertura;
    private Long idUsuario;
}
