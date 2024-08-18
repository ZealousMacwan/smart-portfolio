package studio.zealous.smartportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.zealous.smartportfolio.entity.User;
import studio.zealous.smartportfolio.entity.UserTokensMapping;
import studio.zealous.smartportfolio.repository.jpa.UserRepository;
import studio.zealous.smartportfolio.repository.jpa.UserTokensMappingRepository;
import studio.zealous.smartportfolio.util.time.TimeUtil;

import java.util.Optional;

@Service
public class UserTokenMappingService {
    @Autowired
    UserTokensMappingRepository userTokensMappingRepository;
    @Autowired
    private UserRepository userRepository;

    public String getAngelUserId(String jwtToken) {
        String angelUserId = userTokensMappingRepository.findAngelUserIdByJwtToken(jwtToken);
        return angelUserId;
    }

    public String getApiKey(String jwtToken) {
        String apiKey = userTokensMappingRepository.findApiKeyByJwtToken(jwtToken);
        return apiKey;
    }

    public UserTokensMapping saveUserApiKey(String angelUserId, String apiKey) {
        UserTokensMapping userTokensMapping = new UserTokensMapping();
        Optional<User> user = userRepository.findByAngelUserId(angelUserId);

        user.ifPresent(userTokensMapping::setAngelUserId);
        userTokensMapping.setApiKey(apiKey);
        userTokensMapping.setCreatedAt(TimeUtil.nowWithZone());
        userTokensMapping.setUpdatedAt(TimeUtil.nowWithZone());

        return userTokensMappingRepository.save(userTokensMapping);

    }
    public UserTokensMapping saveUserTokens(String angelUserId, String apiKey, String jwtToken, String refreshToken) {

        UserTokensMapping existingUserTokenMapping = userTokensMappingRepository.findByAngelUserIdAndApiKey(angelUserId, apiKey);
        
        existingUserTokenMapping.setJwtToken(jwtToken);
        existingUserTokenMapping.setRefreshToken(refreshToken);
        existingUserTokenMapping.setUpdatedAt(TimeUtil.nowWithZone());
        return userTokensMappingRepository.save(existingUserTokenMapping);

    }
}
