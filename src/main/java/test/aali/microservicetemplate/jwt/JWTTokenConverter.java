/*
 * Copyright (c) 2017. The Ontario Institute for Cancer Research. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.aali.microservicetemplate.jwt;

import lombok.extern.slf4j.Slf4j;
import test.aali.microservicetemplate.utils.TypeUtils;
import lombok.val;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

@Slf4j
public class JWTTokenConverter extends JwtAccessTokenConverter {

  public JWTTokenConverter(String publicKey) {
    super();
    this.setVerifierKey(publicKey);
  }

  @Override
  public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
    OAuth2Authentication authentication = super.extractAuthentication(map);

    val context = (Map<String, ?>)map.get("context");
    val user = (Map<String, ?>)context.get("user");
    val jwtUser = TypeUtils.convertType(user, JWTUser.class);

    authentication.setDetails(jwtUser);

    return authentication;
  }

}
