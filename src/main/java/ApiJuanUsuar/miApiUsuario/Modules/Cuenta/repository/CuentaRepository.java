package ApiJuanUsuar.miApiUsuario.Modules.Cuenta.repository;

import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

}
