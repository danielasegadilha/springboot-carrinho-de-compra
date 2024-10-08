package br.senac.rj.grupo1.carrinhodecompra.services;

import br.senac.rj.grupo1.carrinhodecompra.repository.ItemCarrinhoRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemCarrinhoService {

    private final ItemCarrinhoRepository itemCarrinhoRepository;

    public ItemCarrinhoService(ItemCarrinhoRepository itemCarrinhoRepository) {
        this.itemCarrinhoRepository = itemCarrinhoRepository;
    }

}