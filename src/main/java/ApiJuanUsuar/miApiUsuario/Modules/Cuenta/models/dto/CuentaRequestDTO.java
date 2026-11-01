package ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaRequestDTO {
    @NotBlank
    private String numeroCuenta;
    @NotBlank
    private String tipoCuenta;
    @NotNull  
    private Double saldo;
    @NotBlank
    private String fechaApertura;
    @NotNull
    private Long idUsuario;
}
