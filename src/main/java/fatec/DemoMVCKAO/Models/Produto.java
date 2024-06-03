package fatec.DemoMVCKAO.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;


@Entity //indica que esta classe é uma entidade JPA, ou seja, ela será mapeada para uma tabela no banco de dados.
@Data //esta anotação do projeto Lombok gera automaticamente métodos como toString(), equals(), hashCode(), getters e setters para os campos da classe.
public class Produto {
    @Id //indica que o campo abaixo é a chave primária da entidade.
    @GeneratedValue(strategy = GenerationType.AUTO) //define a estratégia de geração de valor para o campo id, neste caso, GenerationType.AUTO significa que o provedor de persistência deve escolher automaticamente a estratégia apropriada.
    private UUID id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Quantidade é obrigatório")
    private int quantidade;
    @NotNull(message = "Preço é obrigatório")
    private float preco;

    public Produto() {
    }
}