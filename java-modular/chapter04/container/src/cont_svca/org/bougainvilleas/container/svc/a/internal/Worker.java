package org.bougainvilleas.container.svc.a.internal;

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
        System.out.println("App A doing work within tx " + txId);
        txMgr.commit(txId);
    }
}
