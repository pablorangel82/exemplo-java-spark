
package exemplo.java.spark.services;

import exemplo.java.spark.dao.GenericDao;
import exemplo.java.spark.model.Produto;
import exemplo.java.spark.model.dto.input.ProdutoDTOInput;
import exemplo.java.spark.model.dto.output.ProdutoDTOOutput;
import java.util.Collection;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 11/06/2023
 * @brief  class ProdutoService
 */
public class ProdutoService {
    
    @Inject
    private final GenericDao<Produto> produtoDAO = new GenericDao<Produto>(Produto.class);
    private final ModelMapper modelMapper = new ModelMapper();
    
    public Collection<ProdutoDTOOutput> listarProdutos(){
        produtoDAO.begin();
        Iterable<Produto> listaProdutos = produtoDAO.findAll();
        produtoDAO.end();
        Collection<ProdutoDTOOutput> listaProdutoDTOOutputs = modelMapper.map(listaProdutos, Collection.class);
        return listaProdutoDTOOutputs;
    } 
    
    public void adicionarProduto(ProdutoDTOInput produtoDTOInput){
        Produto produto = modelMapper.map(produtoDTOInput, Produto.class);
        produtoDAO.begin();
        produtoDAO.create(produto);
        produtoDAO.end();
    }
}
