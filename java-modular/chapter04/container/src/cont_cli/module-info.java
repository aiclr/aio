module cont_cli
{
    requires cont_api;

    provides org.bougainvilleas.container.api.tx.TransactionManager
            with org.bougainvilleas.container.cli.tx.SimpleTransactionManager;
}