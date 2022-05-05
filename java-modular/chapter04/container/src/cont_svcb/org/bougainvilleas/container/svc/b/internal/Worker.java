package org.bougainvilleas.container.svc.b.internal;

import org.bougainvilleas.container.api.tx.TransactionManager;

public class Worker implements Runnable
{
    TransactionManager txMgr;

    public Worker()
    {
        txMgr = TransactionManager.getTransactionManagers().next();
    }

    public void run()
    {
        long txId = txMgr.start();
        System.out.println("App B doing work within tx " + txId);
        txMgr.commit(txId);
    }
}
