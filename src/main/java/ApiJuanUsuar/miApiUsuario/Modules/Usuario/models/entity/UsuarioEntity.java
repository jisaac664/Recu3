package ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.entity;

import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.entity.CuentaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@Getter @Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CORREO", unique = true)
    private String correo;
    @Column(name = "FECHAREGISTRO")
    private String fechaRegistro;

    @OneToMany(mappedBy = "usuario")
    private List<CuentaEntity> cuenta = new ArrayList<>();
}
