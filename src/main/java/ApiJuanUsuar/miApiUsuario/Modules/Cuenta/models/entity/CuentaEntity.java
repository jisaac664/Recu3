package ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.entity;

import ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.entity.UsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CUENTA")
@Getter @Setter
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCUENTA")
    private Long id;
    @Column(name = "NUMEROCUENTA")
    private String numeroCuenta;
    @Column(name = "TIPOCUENTA")
    private String tipoCuenta;
    @Column(name = "SALDO")
    private Double saldo;
    @Column(name = "FECHAAPERTURA")
    private String fechaApertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUSUARIO")
    private UsuarioEntity usuario;
}
