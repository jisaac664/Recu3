package ApiJuanUsuar.miApiUsuario.Modules.Usuario.service;

import ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.dto.UsuarioRequestDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.dto.UsuarioResponseDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.entity.UsuarioEntity;
import ApiJuanUsuar.miApiUsuario.Modules.Usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repoUsuario;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository repoUsuario, ModelMapper modelMapper) {
        this.repoUsuario = repoUsuario;
        this.modelMapper = modelMapper;
    }

    /***
     *
     * @return es una lista de objeto ResponseDTO
     */
    public List<UsuarioResponseDTO> getData(){
        //1.Traer los datos de la base, los datos vienen en formato Entity
        List<UsuarioEntity> data = repoUsuario.findAll();
        //2. Retornar los datos encontrados convirtiendole los detos de Entity a ResponseDTO
        return data.stream()
                .map( UsuarioEntity -> modelMapper.map(UsuarioEntity, UsuarioResponseDTO.class))
                .toList();
    }

    public UsuarioResponseDTO ingresarDatos(@Valid UsuarioRequestDTO dtoRequest){
        //Validar si el correo ya existe en la base de datos
        UsuarioEntity data = repoUsuario.findByCorreo(dtoRequest.getCorreo());
        if (data == null){
            UsuarioEntity datosGuardados = repoUsuario.save(modelMapper.map(dtoRequest, UsuarioEntity.class));
            return modelMapper.map(datosGuardados, UsuarioResponseDTO.class);
        }
        return null;
    }
}
