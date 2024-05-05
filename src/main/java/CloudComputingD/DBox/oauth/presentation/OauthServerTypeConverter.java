package CloudComputingD.DBox.oauth.presentation;

import CloudComputingD.DBox.oauth.domain.OauthServerType;
import org.springframework.core.convert.converter.Converter;

public class OauthServerTypeConverter implements Converter<String, OauthServerType> {
    @Override
    public OauthServerType convert(String source) {
        return OauthServerType.fromName(source);
    }
}
