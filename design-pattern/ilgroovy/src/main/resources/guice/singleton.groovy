@Grapes([@Grab('aopalliance:aopalliance:1.0'), @Grab('com.google.code.guice:guice:1.0')])
import com.google.inject.*

/**
 * Singleton Pattern using Guice.
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
    @Inject Calculator calc
    def executeCalc(a, b) { calc.add(a, b) }
    String toString() { 'Client: ' + hashCode() }
}

def injector = Guice.createInjector (
        [configure: { binding ->
            binding.bind(Calculator)
                    .to(CalculatorImpl)
                    .asEagerSingleton() } ] as Module
)

def client = injector.getInstance(Client)
assert 3 == client.executeCalc(1, 2)
println "$client, $client.calc, $client.calc.totalCalculations"

client = injector.getInstance(Client)
assert 4 == client.executeCalc(2, 2)
println "$client, $client.calc, $client.calc.totalCalculations"


println '\n使用注释来表达依赖关系'

@ImplementedBy(Calculator2Impl)
interface Calculator2 {
    def add(a, b)
}

@Singleton
class Calculator2Impl implements Calculator2 {
    private total = 0
    def add(a, b) { total++; a + b }
    def getTotalCalculations() { 'Total Calculations: ' + total }
    String toString() { 'Calc2: ' + hashCode() }
}

class Client2 {
    @Inject Calculator2 calc
    def executeCalc(a, b) { calc.add(a, b) }
    String toString() { 'Client2: ' + hashCode() }
}

def injector2= Guice.createInjector ()

def client2 = injector2.getInstance(Client2)
assert 3 == client2.executeCalc(1, 2)
println "$client2, $client2.calc, $client2.calc.totalCalculations"

client2 = injector2.getInstance(Client2)
assert 4 == client2.executeCalc(2, 2)
println "$client2, $client2.calc, $client2.calc.totalCalculations"