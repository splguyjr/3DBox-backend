package CloudComputingD.DBox.oauth.domain.client;

import CloudComputingD.DBox.entity.User;
import CloudComputingD.DBox.oauth.domain.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    User fetch(String code);
}