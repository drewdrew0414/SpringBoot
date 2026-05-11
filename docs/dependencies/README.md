# 의존성별 사용 예제 문서

이 폴더는 `build.gradle.kts`에 선언된 의존성을 하나씩 뜯어보는 공간이다.

파일 하나가 의존성 하나를 설명한다고 보면 된다.
각 문서에는 아래 내용이 들어 있다.

```
Gradle 선언
↓
이게 뭐하는 의존성인지
↓
언제 쓰면 되는지
↓
설정은 어떻게 잡는지
↓
예제 코드는 어떤 식인지
↓
주의할 점과 자주 하는 실수
```

## 목록

- [implementation - spring-boot-starter-data-jdbc](./implementation-spring-boot-starter-data-jdbc.md)
- [implementation - spring-boot-starter-data-ldap](./implementation-spring-boot-starter-data-ldap.md)
- [implementation - spring-boot-starter-data-rest](./implementation-spring-boot-starter-data-rest.md)
- [implementation - spring-boot-starter-graphql](./implementation-spring-boot-starter-graphql.md)
- [implementation - spring-boot-starter-hateoas](./implementation-spring-boot-starter-hateoas.md)
- [implementation - spring-boot-starter-jdbc](./implementation-spring-boot-starter-jdbc.md)
- [implementation - spring-boot-starter-jersey](./implementation-spring-boot-starter-jersey.md)
- [implementation - spring-boot-starter-ldap](./implementation-spring-boot-starter-ldap.md)
- [implementation - spring-boot-starter-restclient](./implementation-spring-boot-starter-restclient.md)
- [implementation - spring-boot-starter-security](./implementation-spring-boot-starter-security.md)
- [implementation - spring-boot-starter-security-oauth2-authorization-server](./implementation-spring-boot-starter-security-oauth2-authorization-server.md)
- [implementation - spring-boot-starter-security-oauth2-client](./implementation-spring-boot-starter-security-oauth2-client.md)
- [implementation - spring-boot-starter-security-oauth2-resource-server](./implementation-spring-boot-starter-security-oauth2-resource-server.md)
- [implementation - spring-boot-starter-security-saml2](./implementation-spring-boot-starter-security-saml2.md)
- [implementation - spring-boot-starter-session-data-redis](./implementation-spring-boot-starter-session-data-redis.md)
- [implementation - spring-boot-starter-session-jdbc](./implementation-spring-boot-starter-session-jdbc.md)
- [implementation - spring-boot-starter-webclient](./implementation-spring-boot-starter-webclient.md)
- [implementation - spring-boot-starter-webflux](./implementation-spring-boot-starter-webflux.md)
- [implementation - spring-boot-starter-webmvc](./implementation-spring-boot-starter-webmvc.md)
- [implementation - spring-boot-starter-webservices](./implementation-spring-boot-starter-webservices.md)
- [implementation - spring-boot-starter-websocket](./implementation-spring-boot-starter-websocket.md)
- [implementation - graphql-dgs-spring-graphql-starter](./implementation-graphql-dgs-spring-graphql-starter.md)
- [developmentOnly - vaadin-dev](./developmentonly-vaadin-dev.md)
- [implementation - vaadin-spring-boot-starter](./implementation-vaadin-spring-boot-starter.md)
- [implementation - htmx-spring-boot](./implementation-htmx-spring-boot.md)
- [implementation - springdoc-openapi-starter-webmvc-ui](./implementation-springdoc-openapi-starter-webmvc-ui.md)
- [implementation - spring-data-rest-hal-explorer](./implementation-spring-data-rest-hal-explorer.md)
- [implementation - spring-modulith-starter-core](./implementation-spring-modulith-starter-core.md)
- [implementation - spring-modulith-starter-jdbc](./implementation-spring-modulith-starter-jdbc.md)
- [implementation - spring-security-webauthn](./implementation-spring-security-webauthn.md)
- [compileOnly - lombok](./compileonly-lombok.md)
- [developmentOnly - spring-boot-devtools](./developmentonly-spring-boot-devtools.md)
- [developmentOnly - spring-boot-docker-compose](./developmentonly-spring-boot-docker-compose.md)
- [runtimeOnly - mysql-connector-j](./runtimeonly-mysql-connector-j.md)
- [annotationProcessor - spring-boot-configuration-processor](./annotationprocessor-spring-boot-configuration-processor.md)
- [annotationProcessor - lombok](./annotationprocessor-lombok.md)
- [testImplementation - spring-boot-starter-data-jdbc-test](./testimplementation-spring-boot-starter-data-jdbc-test.md)
- [testImplementation - spring-boot-starter-data-ldap-test](./testimplementation-spring-boot-starter-data-ldap-test.md)
- [testImplementation - spring-boot-starter-data-rest-test](./testimplementation-spring-boot-starter-data-rest-test.md)
- [testImplementation - spring-boot-starter-graphql-test](./testimplementation-spring-boot-starter-graphql-test.md)
- [testImplementation - spring-boot-starter-hateoas-test](./testimplementation-spring-boot-starter-hateoas-test.md)
- [testImplementation - spring-boot-starter-jdbc-test](./testimplementation-spring-boot-starter-jdbc-test.md)
- [testImplementation - spring-boot-starter-jersey-test](./testimplementation-spring-boot-starter-jersey-test.md)
- [testImplementation - spring-boot-starter-ldap-test](./testimplementation-spring-boot-starter-ldap-test.md)
- [testImplementation - spring-boot-starter-restclient-test](./testimplementation-spring-boot-starter-restclient-test.md)
- [testImplementation - spring-boot-starter-security-oauth2-authorization-server-test](./testimplementation-spring-boot-starter-security-oauth2-authorization-server-test.md)
- [testImplementation - spring-boot-starter-security-oauth2-client-test](./testimplementation-spring-boot-starter-security-oauth2-client-test.md)
- [testImplementation - spring-boot-starter-security-oauth2-resource-server-test](./testimplementation-spring-boot-starter-security-oauth2-resource-server-test.md)
- [testImplementation - spring-boot-starter-security-saml2-test](./testimplementation-spring-boot-starter-security-saml2-test.md)
- [testImplementation - spring-boot-starter-security-test](./testimplementation-spring-boot-starter-security-test.md)
- [testImplementation - spring-boot-starter-session-data-redis-test](./testimplementation-spring-boot-starter-session-data-redis-test.md)
- [testImplementation - spring-boot-starter-session-jdbc-test](./testimplementation-spring-boot-starter-session-jdbc-test.md)
- [testImplementation - spring-boot-starter-webclient-test](./testimplementation-spring-boot-starter-webclient-test.md)
- [testImplementation - spring-boot-starter-webflux-test](./testimplementation-spring-boot-starter-webflux-test.md)
- [testImplementation - spring-boot-starter-webmvc-test](./testimplementation-spring-boot-starter-webmvc-test.md)
- [testImplementation - spring-boot-starter-webservices-test](./testimplementation-spring-boot-starter-webservices-test.md)
- [testImplementation - spring-boot-starter-websocket-test](./testimplementation-spring-boot-starter-websocket-test.md)
- [testImplementation - graphql-dgs-spring-graphql-starter-test](./testimplementation-graphql-dgs-spring-graphql-starter-test.md)
- [testImplementation - spring-modulith-starter-test](./testimplementation-spring-modulith-starter-test.md)
- [testCompileOnly - lombok](./testcompileonly-lombok.md)
- [testRuntimeOnly - junit-platform-launcher](./testruntimeonly-junit-platform-launcher.md)
- [testAnnotationProcessor - lombok](./testannotationprocessor-lombok.md)
- [mavenBom - spring-modulith-bom](./mavenbom-spring-modulith-bom.md)
- [mavenBom - graphql-dgs-platform-dependencies](./mavenbom-graphql-dgs-platform-dependencies.md)
- [mavenBom - vaadin-bom](./mavenbom-vaadin-bom.md)
