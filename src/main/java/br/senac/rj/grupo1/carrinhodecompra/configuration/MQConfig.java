package br.senac.rj.grupo1.carrinhodecompra.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQConfig {

	@Autowired
	private AmqpAdmin amqpAdmin;
	
	private Queue queue (String queueName){
	
	return new Queue(queueName, true, false, false);
	
	private DirectExchange directExchange(){
	
	return new DirectExchange("ecommercermq"
	,
	
	Binding.DestinationType.QUEUE, directExchange().getName(),
	queue,getName(), null);

}
