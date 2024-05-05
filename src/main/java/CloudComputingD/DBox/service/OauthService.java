package CloudComputingD.DBox.service;

import CloudComputingD.DBox.entity.User;
import CloudComputingD.DBox.repository.OauthMemberRepository;
import CloudComputingD.DBox.oauth.domain.OauthServerType;
import CloudComputingD.DBox.oauth.domain.authcode.AuthCodeRequestUrlProviderComposite;
import CloudComputingD.DBox.oauth.domain.client.OauthMemberClientComposite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public String login(OauthServerType oauthServerType, String authCode) {
        User user = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        User saved = oauthMemberRepository.findByOauthId(user.oauthId())
                .orElseGet(() -> oauthMemberRepository.save(user));
        return saved.oauthId().oauthServerId();
    }
}