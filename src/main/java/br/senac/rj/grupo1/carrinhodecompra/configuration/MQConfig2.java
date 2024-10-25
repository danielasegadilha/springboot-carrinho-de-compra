package br.senac.rj.grupo1.carrinhodecompra.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MQConfig2 {

    @Autowired
    private AmqpAdmin amqpAdmin;
    
    private Queue queue;

    private Queue createQueue(String queueName) {
        return new Queue(queueName, true, false, false);
    }
    
    private DirectExchange createDirectExchange() {
        return new DirectExchange("ecommercermq");
    }
    
    @PostConstruct
    private void create() {
        // Create the queue
        this.queue = createQueue("fila-ecommerce");
        
        // Create the direct exchange
        DirectExchange directExchange = createDirectExchange();
        
        // Create the binding
        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
        
        // Declare the queue, exchange, and binding
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);
    }
}
