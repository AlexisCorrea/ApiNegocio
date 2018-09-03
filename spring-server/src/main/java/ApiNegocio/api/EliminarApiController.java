package ApiNegocio.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import ApiNegocio.model.JsonApiBodyRequestDelete;
import ApiNegocio.model.JsonApiBodyRequestGet;
import ApiNegocio.model.JsonApiBodyResponseErrors;
import ApiNegocio.model.JsonApiBodyResponseSuccess;
import ApiNegocio.model.RegistrarRequest;
import ApiNegocio.repository.negocioRepository;
import ApiNegocio.services.ApiPersonas;
import io.swagger.annotations.*;

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
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T15:25:59.597Z")

@Controller
public class EliminarApiController implements EliminarApi {

	private static final Logger log = LoggerFactory.getLogger(EliminarApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	JsonApiBodyResponseSuccess exito = new JsonApiBodyResponseSuccess();
	JsonApiBodyResponseErrors error = new JsonApiBodyResponseErrors();

	ApiPersonas api_personas = new ApiPersonas();

	@Autowired
	private negocioRepository negocio_repository;

	@org.springframework.beans.factory.annotation.Autowired
	public EliminarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> eliminarDelete(
			@ApiParam(value = "body", required = true) @Valid @RequestBody JsonApiBodyRequestGet body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			//valida que si el id de  l apersona comcuerda con el token para poder eliminarlo 
			if (api_personas.validar_id(body.getNegocio().get(0).getID(),
					body.getNegocio().get(0).getToken())) {
				
				List<RegistrarRequest> negocio = negocio_repository
						.findByIdAdministrador(body.getNegocio().get(0).getID());
				for (RegistrarRequest index : negocio) {
					if (index.getId().equals(body.getNegocio().get(0).getParametro())) {
						negocio_repository.delete(body.getNegocio().get(0).getParametro());
						exito.setId(body.getNegocio().get(0).getID());
						exito.setNombre(index.getNombre());
						exito.setEstado("el negocio fue eliminado exitosamente");
						return new ResponseEntity<JsonApiBodyResponseSuccess>(exito, HttpStatus.OK);

					}
					}
				error.setCodigo("004");
				error.setDetalle("no se encontro el negocio a eliminar");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
			
			}
			error.setCodigo("001");
			error.setDetalle("no tiene permisos de crear negocios");
			return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
		}
		error.setCodigo("003");
		error.setDetalle("error en el header");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);

	}
}
