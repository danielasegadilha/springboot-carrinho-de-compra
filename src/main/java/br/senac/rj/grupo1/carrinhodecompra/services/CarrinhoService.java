package br.senac.rj.grupo1.carrinhodecompra.services;

import br.senac.rj.grupo1.carrinhodecompra.repository.CarrinhoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository){
        this.carrinhoRepository = carrinhoRepository;
    }

}