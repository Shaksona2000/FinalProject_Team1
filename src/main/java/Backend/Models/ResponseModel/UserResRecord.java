package Backend.Models.ResponseModel;

import java.util.List;

public record UserResRecord(String userID, String username, List books) {
}
