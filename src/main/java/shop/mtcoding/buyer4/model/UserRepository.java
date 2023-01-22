package shop.mtcoding.buyer4.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public int insert(@Param("username") String username, @Param("password") String password,
            @Param("email") String email);

    public List<Product> findAll();

    public Product findById(int id);

    public int updateById(@Param("id") String id, @Param("password") String password);

    public int deleteById(int id);
}
// <delete id="deleteById">
// delete from user_tb where id = #{id}
// </delete>
