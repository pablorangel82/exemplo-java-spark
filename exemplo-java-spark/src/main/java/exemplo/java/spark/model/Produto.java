
package exemplo.java.spark.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 11/06/2023
 * @brief  class Produto
 */
@Entity
@Getter
@Setter
public class Produto implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
}
