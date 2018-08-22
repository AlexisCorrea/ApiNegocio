package ApiNegocio.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import ApiNegocio.model.JsonApiBodyRequest;
import ApiNegocio.model.JsonApiBodyResponseErrors;
import ApiNegocio.model.JsonApiBodyResponseSuccess;
import ApiNegocio.repository.negocioRepository;
import ApiNegocio.services.ApiPersonas;
import ApiNegocio.util.CopiAndWrite;
import io.swagger.annotations.*;
import scala.annotation.meta.getter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T15:25:59.597Z")

@Controller
public class RegistrarApiController implements RegistrarApi {

    private static final Logger log = LoggerFactory.getLogger(RegistrarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    ApiPersonas api_personas= new ApiPersonas();
    
    @Autowired
    private negocioRepository negocio_repository;
    
    JsonApiBodyResponseSuccess exito= new JsonApiBodyResponseSuccess();
    
    JsonApiBodyResponseErrors error= new JsonApiBodyResponseErrors();
    
    

    @org.springframework.beans.factory.annotation.Autowired
    public RegistrarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> registrarPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	JSONObject persona=new JSONObject();
        	
        	if (!api_personas.validar_id(body.getNegocio().get(0).getIdAdministrador(),body.getNegocio().get(0).getToken())) {
        		error.setCodigo("001");
				error.setDetalle("no tiene permisos de crear negocios");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);
			}
        	try {
        		body.getNegocio().get(0).setId(CopiAndWrite.leer());
        		CopiAndWrite.escribir();
        		System.out.println(body.getNegocio().get(0));
        		negocio_repository.save(body.getNegocio().get(0));
        		exito.setId(body.getNegocio().get(0).getId());
        		exito.setNombre(body.getNegocio().get(0).getNombre());
        		exito.setEstado("el negocio fue creado exitosamente");
        		return new ResponseEntity<JsonApiBodyResponseSuccess>(exito,HttpStatus.CREATED );
			} catch (Exception e) {
				error.setCodigo("002");
				error.setDetalle("error interno");
				System.out.println(e.getMessage());
				return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);
			}
        	       
    }
        error.setCodigo("003");
		error.setDetalle("error en el header");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error,HttpStatus.BAD_REQUEST);

}
}
