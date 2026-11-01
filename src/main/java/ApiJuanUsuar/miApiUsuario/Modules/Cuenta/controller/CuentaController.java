package ApiJuanUsuar.miApiUsuario.Modules.Cuenta.controller;

import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.dto.CuentaRequestDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.models.dto.CuentaResponseDTO;
import ApiJuanUsuar.miApiUsuario.Modules.Cuenta.service.CuentaService;
import ApiJuanUsuar.miApiUsuario.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/cuenta")
public class CuentaController {

    private final CuentaService service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CuentaResponseDTO>>> obtenerDatos(){
        try{
            //Invocar el metodo del service que me permite obtener los datos
            List<CuentaResponseDTO> dataRespuesta = service.getData();
            if(dataRespuesta != null && !dataRespuesta.isEmpty()){
                //Este proceso se ejecuta cuando hay datos en la base de datos
                ApiResponse<List<CuentaResponseDTO>> respuesta = new ApiResponse<>(
                        true,
                        "Datos encontrados",
                        dataRespuesta);
                log.info("Se consultaron dtos de las cuenta");
                return ResponseEntity.ok(respuesta);
            }
            //Este proceso se ejecutara cuando no haya datos por mostrar
            ApiResponse<List<CuentaResponseDTO>> respuesta = new ApiResponse<>(
                    true,
                    "No hay datos por mostrar");
            log.info("Se consultaron dtos de las cuentas, pero no hay registros");
            return ResponseEntity.ok(respuesta);
        }catch(Exception e){
            //Este proceso se ejecutara no la consulta haya dado error
            ApiResponse<List<CuentaResponseDTO>> respuesta = new ApiResponse<>(
                    false,
                    "Ocurrio un error durante el proceso.");
            log.error("Ocurrio un error en el proceso de consulta.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CuentaResponseDTO>> agregarCuentas(
            @Valid @RequestBody CuentaRequestDTO resquestDTO){
        CuentaResponseDTO data = service.ingresarDatos(resquestDTO);
        if(data != null){
            ApiResponse<CuentaResponseDTO> respuesta = new ApiResponse<>(
                    true,
                    "Datos ingresados exitosamente",
                    data
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        ApiResponse<CuentaResponseDTO> respuesta = new ApiResponse<>(
                false,
                "No se pudo realizar la insercion de datos. Verifique que el idUsuario exista.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
}
