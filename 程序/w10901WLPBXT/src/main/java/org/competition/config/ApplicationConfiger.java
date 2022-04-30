package org.competition.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 全局事务配置
 * REQUIRED ：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
 * SUPPORTS ：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
 * MANDATORY ：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
 * REQUIRES_NEW ：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
 * NOT_SUPPORTED ：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
 * NEVER ：以非事务方式运行，如果当前存在事务，则抛出异常。
 * NESTED ：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于 REQUIRED 。
 * 指定方法：通过使用 propagation 属性设置，例如：@Transactional(propagation = Propagation.REQUIRED)
 */

@Configuration
public class ApplicationConfiger {
	private static final String ASPECTJ_EXPRESSION = "execution(* org.competition.service.*.*(..))";
	
	@Autowired
	private TransactionManager transactionManager;
	 @Bean
	    public TransactionInterceptor txAdvice() {

	        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
	        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

	        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
	        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	        txAttr_REQUIRED_READONLY.setReadOnly(true);

	        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
	        source.addTransactionalMethod("add*", txAttr_REQUIRED);
	        source.addTransactionalMethod("del*", txAttr_REQUIRED);
	        source.addTransactionalMethod("update*", txAttr_REQUIRED);
	        source.addTransactionalMethod("list*", txAttr_REQUIRED);
	        source.addTransactionalMethod("search*", txAttr_REQUIRED);
	        
	        return new TransactionInterceptor(transactionManager, source);
	    }

	    @Bean
	    public Advisor txAdviceAdvisor() {
	        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	        pointcut.setExpression(ASPECTJ_EXPRESSION);
	        return new DefaultPointcutAdvisor(pointcut, txAdvice());
	    }

}
