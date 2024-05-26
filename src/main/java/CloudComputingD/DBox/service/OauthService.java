package CloudComputingD.DBox.service;

import CloudComputingD.DBox.entity.Folder;
import CloudComputingD.DBox.entity.User;
import CloudComputingD.DBox.repository.FolderRepository;
import CloudComputingD.DBox.repository.UserRepository;
import CloudComputingD.DBox.oauth.domain.OauthServerType;
import CloudComputingD.DBox.oauth.domain.authcode.AuthCodeRequestUrlProviderComposite;
import CloudComputingD.DBox.oauth.domain.client.OauthMemberClientComposite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final UserRepository oauthMemberRepository;
    private final FolderRepository folderRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public String login(OauthServerType oauthServerType, String authCode) {
        User user = oauthMemberClientComposite.fetch(oauthServerType, authCode);
//        User saved = oauthMemberRepository.findByOauthId(user.oauthId())
//                .orElseGet(() -> oauthMemberRepository.save(user));

        Optional<User> optionalUser = oauthMemberRepository.findByOauthId(user.oauthId());

        optionalUser.ifPresentOrElse(
                savedUser -> {},
                () -> {
                    oauthMemberRepository.save(user);

                    //유저 정보 저장 후 해당하는 유저의 root 폴더까지 생성하여 저장
                    Folder folder = Folder.builder()
                            .name(user.oauthId().oauthServerId() + "_root")
                            .created_date(LocalDateTime.now())
                            .is_root(true)
                            .user(user)
                            .build();
                    folderRepository.save(folder);
                }
        );
        optionalUser = oauthMemberRepository.findByOauthId(user.oauthId());

        return optionalUser.get().oauthId().oauthServerId();

    }

    public String getProfileImageLink(String oauthServerId) {
        User user = oauthMemberRepository.findByOauthServerId(oauthServerId);
        return user.profileImageUrl();
    }


}