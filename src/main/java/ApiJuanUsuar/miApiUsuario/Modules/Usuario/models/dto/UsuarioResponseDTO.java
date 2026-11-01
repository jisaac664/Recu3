package ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String fechaRegistro;
}
