@Grab('org.springframework:spring-context:3.2.2.RELEASE')
import org.springframework.context.support.ClassPathXmlApplicationContext

interface Calc {
    def add(a, d)
}

class CalcImpl implements Calc {
    @Override
    def add(a, b) { a + b }
}

def ctx=new ClassPathXmlApplicationContext('beans.xml')
def calc=ctx.getBean('calc')
println calc.add(3,25)

//run with `groovy decorator.groovy`