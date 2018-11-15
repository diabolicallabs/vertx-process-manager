package com.diabolicallabs.process.manager;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import com.diabolicallabs.process.manager.service.KnowledgeService;
import com.diabolicallabs.process.manager.service.KnowledgeServiceFactory;
import com.diabolicallabs.process.manager.service.KnowledgeServiceFactoryImpl;
import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.serviceproxy.ProxyHelper;
import io.vertx.serviceproxy.ServiceBinder;

public class Verticle extends AbstractVerticle {

  private static PoolingDataSource ds;

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    if (ds == null) {
      String dbDirectory = config().getString("db_directory", "/tmp/data/bpmdb");

      ds = new PoolingDataSource();
      ds.setUniqueName("jdbc/jbpm-ds");
      ds.setClassName("org.h2.jdbcx.JdbcDataSource");
      ds.setMaxPoolSize(3);
      ds.setAllowLocalTransactions(true);
      ds.getDriverProperties().put("user", "sa");
      ds.getDriverProperties().put("password", "sasa");
      ds.getDriverProperties().put("URL", "jdbc:h2:file:" + dbDirectory);
      ds.init();
    }

    KnowledgeServiceFactoryImpl serviceImpl = new KnowledgeServiceFactoryImpl(getVertx(), config());

    new ServiceBinder(vertx.getDelegate())
        .setAddress(KnowledgeServiceFactory.DEFAULT_ADDRESS)
        .register(KnowledgeServiceFactory.class, serviceImpl);

    startFuture.complete();

  }
}
