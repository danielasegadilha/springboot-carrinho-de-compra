package br.senac.rj.grupo1.carrinhodecompra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senac.rj.grupo1.carrinhodecompra.entities.Carrinho;
import jakarta.transaction.Transactional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer>{
	
	Optional<Carrinho> findByIdAndStatusNot(int id, int status);

	@Query("SELECT c FROM Carrinho c WHERE c.status != -1")
	List<Carrinho> findCarrinhosAtivos();
	
	@Query("SELECT c FROM Carrinho c WHERE c.status = 2 AND c.usuarioId = :usuarioId")
    List<Carrinho> findCarrinhosFinalizadosByUserId(@Param("usuarioId") int usuarioId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE carts c SET c.status = -1 WHERE c.id = :id", nativeQuery = true)
	List<Carrinho> softDeleteCarrinhoById(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE carts c SET c.status = 2 WHERE c.id = :id", nativeQuery = true)
	void FinalizarCarrinhoById(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO carts (status, total, user_id) VALUES (1, 0.00, :user_id)", nativeQuery = true)
	void createCarrinho(@Param("user_id") int userId);
	
	Carrinho findTopByUsuarioIdOrderByIdDesc(int usuarioId);
}
