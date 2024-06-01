package fatec.DemoMVCKAO.Models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private int quantidade;
    private float preco;

    public Produto() {
    }
}