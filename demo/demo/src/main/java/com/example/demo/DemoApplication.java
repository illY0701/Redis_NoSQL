package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Connection;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception{
		Jedis jedis = new Jedis("redis://default:y0EirjDM0L323e8IJqbHK6VYqbzmjcOR@redis-10396.c308.sa-east-1-1.ec2.cloud.redislabs.com:10396");
		Connection connection = jedis.getConnection();

		String user1 = "{nome:'Anna, usuario:'isa', senha:'12345'}";
		connection.connect();
		jedis.set("User:1",user1); //Adiciona usuário
		System.out.println(jedis.get("User:1"));

		jedis.del("User:1"); //Deleta usuário
		System.out.println(jedis.get("User:1"));
		connection.close();

	 // Adiciona uma tarefa
	 jedis.hset("tarefas", "1", "Comprar leite");
	 jedis.hset("tarefas", "2", "Enviar e-mail para cliente");
	 System.out.println("Tarefas adicionadas com sucesso.");

	 // Lista as tarefas
	 System.out.println("\nLista de Tarefas:");
	 jedis.hgetAll("tarefas").forEach((key, value) -> System.out.println(key + ": " + value));

	 // Marca uma tarefa como concluída
	 jedis.hset("tarefas_concluidas", "1", jedis.hget("tarefas", "1"));
	 jedis.hdel("tarefas", "1");
	 System.out.println("\nTarefa 1 marcada como concluída.");

	 // Remove uma tarefa
	 jedis.hdel("tarefas", "2");
	 System.out.println("\nTarefa 2 removida.");

	 jedis.close();
 		} catch (Exception e) {
	 	System.err.println("Erro ao conectar ao Redis: " + e.getMessage());
 		}
	}

}
