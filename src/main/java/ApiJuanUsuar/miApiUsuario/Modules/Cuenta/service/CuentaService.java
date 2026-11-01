package ApiJuanUsuar.miApiUsuario.Modules.Cuenta.service;

import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.dto.CuentaRequestDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.dto.CuentaResponseDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.entity.CuentaEntity;
import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.repository.CuentaRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    private final CuentaRepository repoCuenta;
    private final ModelMapper modelMapper;

    public CuentaService(CuentaRepository repoCuenta, ModelMapper modelMapper) {
        this.repoCuenta = repoCuenta;
        this.modelMapper = modelMapper;
    }

    /***
     *
     * @return es una lista de objeto ResponseDTO
     */
    public List<CuentaResponseDTO> getData(){
        //1.Traer los datos de la base, los datos vienen en formato Entity
        List<CuentaEntity> data = repoCuenta.findAll();
        //2. Retornar los datos encontrados convirtiendole los detos de Entity a ResponseDTO
        return data.stream()
                .map( CuentaEntity -> modelMapper.map(CuentaEntity, CuentaResponseDTO.class))
                .toList();
    }

    public CuentaResponseDTO ingresarDatos(@Valid CuentaRequestDTO dtoRequest){
        CuentaEntity datosGuardados = repoCuenta.save(modelMapper.map(dtoRequest, CuentaEntity.class));
        return modelMapper.map(datosGuardados, CuentaResponseDTO.class);
    }
}
