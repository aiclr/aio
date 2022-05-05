package org.bougainvilleas.container.cli.tx;

import org.bougainvilleas.container.api.tx.TransactionManager;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleTransactionManager implements TransactionManager
{

  private AtomicLong nextTxId = new AtomicLong();

  @Override
  public long start() {
    return nextTxId.getAndIncrement();
  }

  @Override
  public void commit(long txId) {
    if(txId >= nextTxId.get())
      throw new IllegalStateException("Cannot commit tx with unknown id " + txId);

    System.out.println("Committing tx with id " + txId);
  }

  @Override
  public void rollback(long txId) {
    if(txId >= nextTxId.get())
      throw new IllegalStateException("Cannot rollback tx with unknown id " + txId);

    System.out.println("Rolling back tx with id " + txId);
  }

}
