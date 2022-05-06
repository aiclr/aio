module svc_b
{
    requires api;
    provides org.bougainvilleas.ilj.api.Api
            with org.bougainvilleas.ilj.b.B;
}