package Backend.Models.RequestModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthReqModel {
    private String userName;
    private String password;
}
