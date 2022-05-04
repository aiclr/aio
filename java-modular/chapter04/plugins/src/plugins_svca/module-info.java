module plugins_svca {
    requires plugins_api;
    provides org.bougainvilleas.plugins.api.Api
            with org.bougainvilleas.plugins.a.A;
}