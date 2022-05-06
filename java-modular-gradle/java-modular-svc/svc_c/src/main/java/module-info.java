module svc_c
{
    requires api;
    provides org.bougainvilleas.ilj.api.Api
            with org.bougainvilleas.ilj.c.C;
}