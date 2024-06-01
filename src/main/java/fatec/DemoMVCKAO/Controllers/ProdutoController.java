package fatec.DemoMVCKAO.Controllers;

import fatec.DemoMVCKAO.Models.Produto;
import fatec.DemoMVCKAO.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    private List<Produto> produtos = new ArrayList<Produto>();

    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService _produtoService)
    {
        produtoService = _produtoService;
    }

    @GetMapping
    public String produtos(Model model){
        model.addAttribute("produtos",produtos);
        return "Produtos";
    }

    @PostMapping("/create-produto")
    public ResponseEntity<Object> createChat(@RequestBody Produto produto) {
        try {
            Produto createdProduto = produtoService.createProduto(produto);
            return new ResponseEntity(createdProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        try {
            List<Produto> allProdutos = produtoService.getAllProdutos();
            return new ResponseEntity<>(allProdutos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
        try {
            Produto produto = produtoService.getProdutoById(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        try {
            Produto updatedProduto = produtoService.updateProduto(id, produto);
            return new ResponseEntity<>(updatedProduto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Integer id) {
        try {
            produtoService.deleteProduto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}