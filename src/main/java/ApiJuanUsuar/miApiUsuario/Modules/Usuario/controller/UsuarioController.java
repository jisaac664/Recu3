package ApiJuanUsuar.miApiUsuario.Modules.Usuario.controller;

import ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.dto.UsuarioRequestDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Usuario.models.dto.UsuarioResponseDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Usuario.service.UsuarioService;
import ApiJuanUsuar.miApiUsuario.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> obtenerDatos(){
        try{
            //Invocar el metodo del service que me permite obtener los datos
            List<UsuarioResponseDTO> dataRespuesta = service.getData();
            if(dataRespuesta != null && !dataRespuesta.isEmpty()){
                //Este proceso se ejecuta cuando hay datos en la base de datos
                ApiResponse<List<UsuarioResponseDTO>> respuesta = new ApiResponse<>(
                        true,
                        "Datos encontrados",
                        dataRespuesta);
                log.info("Se consultaron dtos de los usuarios");
                return ResponseEntity.ok(respuesta);
            }
            //Este proceso se ejecutara cuando no haya datos por mostrar
            ApiResponse<List<UsuarioResponseDTO>> respuesta = new ApiResponse<>(
                    true,
                    "No hay datos por mostrar");
            log.info("Se consultaron dtos de los usuarios, pero no hay registros");
            return ResponseEntity.ok(respuesta);
        }catch(Exception e){
            //Este proceso se ejecutara no la consulta haya dado error
            ApiResponse<List<UsuarioResponseDTO>> respuesta = new ApiResponse<>(
                    false,
                    "Ocurrio un error durante el proceso.");
            log.error("Ocurrio un error en el proceso de consulta.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> agregarEstudiante(
            @Valid @RequestBody UsuarioRequestDTO resquestDTO){
        UsuarioResponseDTO data = service.ingresarDatos(resquestDTO);
        if(data != null){
            ApiResponse<UsuarioResponseDTO> respuesta = new ApiResponse<>(
                    true,
                    "Datos ingresados exitosamente",
                    data
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        ApiResponse<UsuarioResponseDTO> respuesta = new ApiResponse<>(
                false,
                "No se pudo realizar la insercion de datos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
}
