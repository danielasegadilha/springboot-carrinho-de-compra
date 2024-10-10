package br.senac.rj.grupo1.carrinhodecompra.controllers;

import br.senac.rj.grupo1.carrinhodecompra.dto.ItemCarrinhoDTO;
import br.senac.rj.grupo1.carrinhodecompra.entities.ItemCarrinho;
import br.senac.rj.grupo1.carrinhodecompra.service.ItemCarrinhoService;
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

    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Void> updateItemQuantity(@PathVariable int cartId, @PathVariable int itemId, @RequestBody ItemCarrinhoDTO itemCarrinhoDTO) {
        Optional<ItemCarrinho> existingItem = itemCarrinhoService.getItemById(itemId);
        if (existingItem.isPresent()) {
            itemCarrinhoService.updateItemQuantity(itemId, itemCarrinhoDTO.getQuantidade());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable int cartId, @PathVariable int itemId) {
        Optional<ItemCarrinho> existingItem = itemCarrinhoService.getItemById(itemId);
        if (existingItem.isPresent()) {
            itemCarrinhoService.deleteItemFromCart(cartId, itemId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
