module plugins_svcb {
    requires plugins_api;
    provides org.bougainvilleas.plugins.api.Api
            with org.bougainvilleas.plugins.b.B;
}