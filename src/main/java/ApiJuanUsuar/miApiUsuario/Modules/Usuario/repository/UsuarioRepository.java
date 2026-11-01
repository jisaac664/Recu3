package ApiJuanUsuar.miApiUsuario.Modules.Usuario.repository;

import ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByCorreo(String correo);
}
