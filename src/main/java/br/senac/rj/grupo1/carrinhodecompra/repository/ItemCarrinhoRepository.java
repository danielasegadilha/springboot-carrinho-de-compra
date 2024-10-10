package br.senac.rj.grupo1.carrinhodecompra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {

    @Query("SELECT i FROM ItemCarrinho i WHERE i.carrinhoId.id = :cartId")
    List<ItemCarrinho> findItemsByCartId(@Param("cartId") int cartId);

    @Modifying
    @Query("DELETE FROM ItemCarrinho i WHERE i.carrinhoId.id = :cartId")
    void deleteItemsByCartId(@Param("cartId") int cartId);

    @Modifying
    @Query("UPDATE ItemCarrinho i SET i.quantidade = :quantidade WHERE i.id = :id")
    void updateItemQuantity(@Param("id") int id, @Param("quantidade") int quantidade);
}
