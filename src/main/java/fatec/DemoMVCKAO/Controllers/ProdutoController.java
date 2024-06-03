package fatec.DemoMVCKAO.Controllers;

import fatec.DemoMVCKAO.Models.Produto;
import fatec.DemoMVCKAO.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller //indica que esta classe é um controlador Spring MVC.
@RequestMapping("/produto") //mapeia as requisições HTTP que começam com /produto para este controlador.
public class ProdutoController {
    private List<Produto> produtos = new ArrayList<Produto>();

    private ProdutoService produtoService;

    @Autowired //injeta uma instância de ProdutoService neste controlador.
    public ProdutoController(ProdutoService _produtoService)
    {
        produtoService = _produtoService;
    }

    @GetMapping //mapeia as requisições GET para o método produtos().
    public String produtos(Model model){
        model.addAttribute("produtos",produtos);
        return "Produtos";
    }

    @PostMapping("/create-produto") //mapeia as requisições POST para o método createProduto().
    public ResponseEntity<Object> createProduto(@RequestBody Produto produto) {
        try {
            Produto createdProduto = produtoService.createProduto(produto);
            return new ResponseEntity(createdProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all") //mapeia as requisições GET para o método getAllProdutos().
    public ResponseEntity<List<Produto>> getAllProdutos() {
        try {
            List<Produto> allProdutos = produtoService.getAllProdutos();
            return new ResponseEntity<>(allProdutos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") //mapeia as requisições GET para o método getProdutoById().
    public ResponseEntity<Produto> getProdutoById(@PathVariable UUID id) {
        try {
            Produto produto = produtoService.getProdutoById(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}") //mapeia as requisições PUT para o método updateProduto().
    public ResponseEntity<Object> updateProduto(@PathVariable UUID id, @RequestBody Produto produto) {
        try {
            Produto updatedProduto = produtoService.updateProduto(id, produto);
            return new ResponseEntity<>(updatedProduto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}") //mapeia as requisições DELETE para o método deleteProduto().
    public ResponseEntity<Object> deleteProduto(@PathVariable UUID id) {
        try {
            produtoService.deleteProduto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}