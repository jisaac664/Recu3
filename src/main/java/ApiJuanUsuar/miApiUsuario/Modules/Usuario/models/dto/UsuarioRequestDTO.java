package ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank @Email
    private String correo;
    @NotBlank
    private String fechaRegistro;
}
