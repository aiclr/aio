@Grapes([
        @Grab('org.springframework:spring-core:3.2.2.RELEASE'),
        @Grab('org.springframework:spring-beans:3.2.2.RELEASE')
])
import org.springframework.beans.factory.support.*

/**
 * Spring Example
 */
interface Calculator {
    def add(a, b)
}

class CalculatorImpl implements Calculator {
    private total = 0

    def add(a, b) { total++; a + b }

    def getTotalCalculations() { 'Total Calculations: ' + total }

    String toString() { 'Calc: ' + hashCode() }
}

class Client {
    Client(Calculator calc) { this.calc = calc }
    def calc

    def executeCalc(a, b) { calc.add(a, b) }

    String toString() { 'Client: ' + hashCode() }
}

def factory = new DefaultListableBeanFactory()
factory.registerBeanDefinition('calc', new RootBeanDefinition(CalculatorImpl))
def beanDef = new RootBeanDefinition(Client, false)
beanDef.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_AUTODETECT)
factory.registerBeanDefinition('client', beanDef)

def client = factory.getBean('client')
assert 3 == client.executeCalc(1, 2)
println "$client,$client.calc,$client.calc.totalCalculations"
client = factory.getBean('client')
assert 4 == client.executeCalc(2, 2)
println "$client,$client.calc,$client.calc.totalCalculations"