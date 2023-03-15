package pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("users")
@Data
public class user
{
    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = id;
    }

    String id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;



}

