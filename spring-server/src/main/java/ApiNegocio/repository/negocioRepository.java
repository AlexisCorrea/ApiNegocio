 package ApiNegocio.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import ApiNegocio.model.RegistrarRequest;
@EnableScan
public interface  negocioRepository extends CrudRepository<RegistrarRequest, String> {
	public List<RegistrarRequest> findByIdAdministrador(String id);
	public List<RegistrarRequest> findById(String id);
	public List<RegistrarRequest> findByTipo(String tipo);
}
