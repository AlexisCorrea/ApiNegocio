package ApiNegocio.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import ApiNegocio.model.BodyFiltrado;
import ApiNegocio.model.JsonApiBodyRequest;
import ApiNegocio.model.JsonApiBodyRequestGet;
import ApiNegocio.model.JsonApiBodyResponseErrors;
import ApiNegocio.model.JsonApiBodyResponseSuccess;
import ApiNegocio.model.RegistrarRequest;
import ApiNegocio.repository.negocioRepository;
import ApiNegocio.services.ApiPersonas;
import io.swagger.annotations.*;
import ApiNegocio.util.*;

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
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T15:25:59.597Z")

@Controller
public class ListarApiController implements ListarApi {

	private static final Logger log = LoggerFactory.getLogger(ListarApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	CopiAndWrite copiaAnddWrite = new CopiAndWrite();

	ApiPersonas api_personas = new ApiPersonas();

	@Autowired
	private negocioRepository negocio_repository;

	JsonApiBodyResponseSuccess exito = new JsonApiBodyResponseSuccess();

	JsonApiBodyResponseErrors error = new JsonApiBodyResponseErrors();

	@org.springframework.beans.factory.annotation.Autowired
	public ListarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> listarIdtrabajadorPost(
			@ApiParam(value = "body", required = true) @Valid @RequestBody JsonApiBodyRequestGet body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				if (body.getNegocio().get(0).getParametro().equals(null)
						|| body.getNegocio().get(0).getParametro().isEmpty()) {
					// valida que si el parametro esta vacio lo saque
					error.setCodigo("006");
					error.setDetalle("el parametro no puede ser vacio");
					return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
				}
				System.out.println(body.getNegocio().get(0).getID());
				if (body.getNegocio().get(0).getID().equals("00")) {
					List<RegistrarRequest> negocios = (List<RegistrarRequest>) negocio_repository.findAll();
					JsonApiBodyRequest obj = new JsonApiBodyRequest();
					obj.setNegocio(negocios);
					return new ResponseEntity<JsonApiBodyRequest>(obj,HttpStatus.OK);
				}
				if (!api_personas.validar_id(body.getNegocio().get(0).getID(), body.getNegocio().get(0).getToken())) {
					error.setCodigo("007");
					error.setDetalle("no tiene permisos de listar negocios");
					return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
				}
				if (body.getNegocio().get(0).getParametro().equals("todo")) {

					List<RegistrarRequest> negocios = negocio_repository
							.findByIdAdministrador(body.getNegocio().get(0).getID());
					if (negocios.isEmpty() || negocios.equals(null)) {
						error.setCodigo("008");
						error.setDetalle("error no exite negocio");
						return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
					}
					JsonApiBodyRequest obj = new JsonApiBodyRequest();
					obj.setNegocio(negocios);
					return new ResponseEntity<JsonApiBodyRequest>(obj, HttpStatus.OK);
				} else {
					List<RegistrarRequest> negocios = negocio_repository
							.findById((body.getNegocio().get(0).getParametro()));
					if (negocios.isEmpty() || negocios.equals(null)) {
						error.setCodigo("008");
						error.setDetalle("error no exite negocio");
						return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
					}
					JsonApiBodyRequest obj = new JsonApiBodyRequest();
					obj.setNegocio(negocios);
					return new ResponseEntity<JsonApiBodyRequest>(obj, HttpStatus.OK);
				}
			} catch (Exception e) {
				error.setCodigo("009");
				error.setDetalle("error interno");
				return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
			}
		}

		error.setCodigo("003");
		error.setDetalle("error en el header");
		return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> obtenerIDsiguiente() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			return new ResponseEntity<String>(copiaAnddWrite.leer(),HttpStatus.OK);
		}else {
			return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public ResponseEntity<?> listarTipoPost(@ApiParam(value = "", required = true) @PathVariable("tipo") String tipo) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			List<RegistrarRequest> negocio = new ArrayList<>();
			negocio_repository.findByTipo(tipo).forEach(negocio::add);
			JsonApiBodyRequest body = new JsonApiBodyRequest();
			body.setNegocio(negocio);
		
			return new ResponseEntity<JsonApiBodyRequest>(body,HttpStatus.OK);
		}else {
			return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public ResponseEntity<?> listarporid(@ApiParam(value = "", required = true) @PathVariable("id") String id){
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			List<RegistrarRequest> negocio = new ArrayList<>();
			negocio_repository.findById(id).forEach(negocio::add);
			JsonApiBodyRequest body = new JsonApiBodyRequest();
			body.setNegocio(negocio);
		
			return new ResponseEntity<JsonApiBodyRequest>(body,HttpStatus.OK);
		}else {
			return new ResponseEntity<JsonApiBodyResponseErrors>(error, HttpStatus.BAD_REQUEST);
		}
	}

	

}
