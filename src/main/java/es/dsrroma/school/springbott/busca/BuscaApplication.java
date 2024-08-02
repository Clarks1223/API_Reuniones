package es.dsrroma.school.springbott.busca;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BuscaApplication {
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

    public static void main(String[] args) {
        SpringApplication.run(BuscaApplication.class, args);
    }

}