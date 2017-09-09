package org.eventapp.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

  /**
   * Creates the datasource bean.
   *
   * @return the datasource bean
   */
  @Bean
  public DataSource dataSource() {
    final MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/eventapp");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("");
    return mysqlDataSource;
  }

  /**
   * Contains the entity manager factory definition.
   *
   * @return {@link LocalContainerEntityManagerFactoryBean}.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
    hibernateJpaVendorAdapter.setShowSql(false);

    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan("com.eventapp.datamodels");
    entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

    return entityManagerFactoryBean;
  }

  /**
   * Contains the transaction manager definition.
   *
   * @return {@link JpaTransactionManager}.
   */
  @Bean
  public JpaTransactionManager transactionManager() {

    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return jpaTransactionManager;
  }

}
