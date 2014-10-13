package eu.ibacz.swsc.spring.di.testdependencyinjection.aspect;

import eu.ibacz.swsc.spring.commons.springdemocommons.Notifier;
import eu.ibacz.swsc.spring.di.testdependencyinjection.dto.Customer;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class BankServiceLoggingAspect {
    
    private Notifier notifier;
    
    @Around("execution(* eu.ibacz.swsc.spring.di.testdependencyinjection.service.BankService.createNewCustomer(..)) && args(firstname, lastname) )")
    public Object onNewCustomerCreated(ProceedingJoinPoint pjp, String firstname, String lastname) throws Throwable { 
        StringBuilder messageBuilder = new StringBuilder("Tesne pred vytvorenim noveho klienta se jmenem '").append(firstname)
                .append("' a prijmenim '").append(lastname).append("'.");
        notifier.notify(messageBuilder.toString());
        
        Object result = pjp.proceed(); //tady se zavola puvodni metoda, na kterou je aspekt zaveseny
        
        messageBuilder = new StringBuilder("Klient '").append(firstname).append(" ").append(lastname)
                .append("' byl uspesne vytvoren.");
        notifier.notify(messageBuilder.toString());
        
        return result;
    }
    
    @Around("execution(* eu.ibacz.swsc.spring.di.testdependencyinjection.service.BankService.getAllCustomers(..)))")
    public Object onGetAllCustomers(ProceedingJoinPoint pjp) throws Throwable { 
        StringBuilder messageBuilder = new StringBuilder("Tesne pred vyhledanim vsech zakazniku");
        notifier.notify(messageBuilder.toString());
        
        List<Customer> result = (List<Customer>) pjp.proceed(); //tady se zavola puvodni metoda, na kterou je aspekt zaveseny
        messageBuilder = new StringBuilder("Zakaznici byli uspesne vyhledaní");
        messageBuilder.append("Celkem").append(result.size()).append(" zakazniku");
        for(Customer customer : result){
            messageBuilder.append(customer.getFirstname()).append(customer.getLastname());
        }
        notifier.notify(messageBuilder.toString());       
        return result;
    }

    /**
     * @return the notifier
     */
    public Notifier getNotifier() {
        return notifier;
    }

    /**
     * @param notifier the notifier to set
     */
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
}
