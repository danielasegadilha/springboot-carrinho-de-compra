package br.senac.rj.grupo1.carrinhodecompra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;
import jakarta.transaction.Transactional;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {

    @Query("SELECT i FROM ItemCarrinho i WHERE i.carrinhoId.id = :cartId")
    List<ItemCarrinho> findItemsByCartId(@Param("cartId") int cartId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ItemCarrinho i WHERE i.carrinhoId.id = :cartId AND i.produtoId = :produtoId")
    void deleteItemFromCart(@Param("cartId") int cartId, @Param("produtoId") long produtoId);

    @Modifying
    @Transactional
    @Query("UPDATE ItemCarrinho i SET i.quantidade = :quantidade WHERE i.carrinhoId.id = :cartId AND i.produtoId = :produtoId")
    void updateItemQuantity(@Param("cartId") int cartId, @Param("produtoId") long produtoId, @Param("quantidade") int quantidade);

    @Query("SELECT i FROM ItemCarrinho i WHERE i.carrinhoId.id = :cartId AND i.produtoId = :produtoId")
    Optional<ItemCarrinho> findByCartIdAndProductId(@Param("cartId") int cartId, @Param("produtoId") long produtoId);


}
