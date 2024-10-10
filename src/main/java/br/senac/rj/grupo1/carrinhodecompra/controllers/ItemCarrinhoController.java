package br.senac.rj.grupo1.carrinhodecompra.controllers;

import br.senac.rj.grupo1.carrinhodecompra.dto.ItemCarrinhoDTO;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;
import br.senac.rj.grupo1.carrinhodecompra.services.ItemCarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class ItemCarrinhoController {

    @Autowired
    private ItemCarrinhoService itemCarrinhoService;

    @PostMapping("/{cartId}/items")
    public ResponseEntity<ItemCarrinho> addItemToCart(@PathVariable int cartId, @RequestBody ItemCarrinhoDTO itemCarrinhoDTO) {
        ItemCarrinho newItem = itemCarrinhoService.addItemToCart(cartId, itemCarrinhoDTO);
        return ResponseEntity.ok(newItem);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<ItemCarrinho>> getItemsByCartId(@PathVariable int cartId) {
        List<ItemCarrinho> items = itemCarrinhoService.getItemsByCartId(cartId);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Void> updateItemQuantity(@PathVariable int cartId, @RequestBody ItemCarrinhoDTO itemCarrinhoDTO) {
        Optional<ItemCarrinho> existingItem = itemCarrinhoService.getItemByCartIdAndProductId(cartId, itemCarrinhoDTO.getProdutoId());
        if (existingItem.isPresent()) {
            itemCarrinhoService.updateItemQuantity(cartId, itemCarrinhoDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cartId}/items/{produtoId}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable int cartId, @PathVariable long produtoId) {
        Optional<ItemCarrinho> existingItem = itemCarrinhoService.getItemByCartIdAndProductId(cartId, produtoId);
        if (existingItem.isPresent()) {
            itemCarrinhoService.deleteItemFromCart(cartId, produtoId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
