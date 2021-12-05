export const environment = {
  production: true,
  serverUrl: '/api',
  keycloak: {
    // Url of the Identity Provider
    issuer: 'http://localhost:8050/auth/',
    // Realm
    realm: 'mamunassessment',
    clientId: 'mamunassessment',
  }
};
