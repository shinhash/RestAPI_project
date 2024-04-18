package hash.dev.api.sign.service;

import java.util.Map;

public interface SignService {

	Map<String, Object> signInUser(Map<String, Object> map) throws Exception;

}
