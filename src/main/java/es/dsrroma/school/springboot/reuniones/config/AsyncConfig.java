package es.dsrroma.school.springboot.reuniones.config;

import es.dsrroma.school.springboot.reuniones.async.BuscaListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class AsyncConfig {
    private static final String QUEUE_NAME = "busca-personas";
    private static final String EXCHANGE_NAME = "servicios";

    @Bean
    //Cola
    public Queue queue() {
        //se maraca como falso para decir que la cola no sobrevivira despues del servidor
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        //Nombre del intercambiador
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    //recibe la cola y el intercambiador
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("reuniones.busca.personas");
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(BuscaListener listener) {
        //el nombre del metodo que pusimos en el buscaListener
        return new MessageListenerAdapter(listener, "recibirMensaje");
    }

    @Bean
    public SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
