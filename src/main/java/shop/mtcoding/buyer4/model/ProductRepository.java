package shop.mtcoding.buyer4.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRepository {
    public int insert(@Param("name") String name, @Param("price") String price,
            @Param("qty") String qty);

    public List<Product> findAll();

    public Product findById(int id);

    public int updateById(@Param("name") String name, @Param("price") String price, @Param("qty") String qty,
            @Param("id") String id);

    public int deleteById(int id);
}